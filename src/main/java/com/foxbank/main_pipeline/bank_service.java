package com.foxbank.main_pipeline;

import com.foxbank.customer.Transaction_service;
import com.foxbank.template.Transaction;
import com.foxbank.template.gen_rand_numb;
import com.foxbank.customer.Account_Services;
import com.foxbank.customer.Customer_Services;
import com.foxbank.template.Accounts;
import com.foxbank.template.Customers;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class bank_service {
    private final Account_Services m_account_services;
    private final Customer_Services m_customer_services;
    private final Transaction_service m_transaction_services;

    public bank_service() {
        this.m_customer_services = new Customer_Services();
        this.m_account_services = new Account_Services();
        m_transaction_services = new Transaction_service();
    }

    public Customers make_new_customer(String name, int age, String address, String phone) {
        return m_customer_services.save(new Customers(name, age, address, phone));
    }

    public Customers remove_from_db(String name) {
        return m_customer_services.remove(new Customers(name));
    }

    public Accounts make_new_account(int customerID, String accountType) {
        Customers customers = m_customer_services.find_all_in_db_by_id(customerID);
        if (customers == null) throw new RuntimeException("Customer not found!");
        String generate_random_number = gen_rand_numb.generate_account_number();
        return m_account_services.save(new Accounts(customerID, generate_random_number, accountType));
    }

    public List<Customers> find_all_customers() {
        return m_customer_services.find_all_in_db();
    }

    public Accounts toggle_active_account(String account_ID) {
        Accounts accounts = m_account_services.active_disable_account(new Accounts(account_ID));
        if (accounts == null) {
            throw new RuntimeException("account wasn't in database");
        }
        return accounts;
    }

    public Transaction depositAmount(int accountNumber, BigDecimal amount, String description) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Balance is not enough");
        Accounts account = m_account_services.find_by_id(accountNumber);
        if (account == null)
            throw new IllegalArgumentException("Account not found");

        account.setBalance(account.getBalance().add(amount));
        m_account_services.updateBalance(account);

        Transaction t = new Transaction(String.valueOf(account.getCustomer_ID()), "Deposit", amount, description);
        return m_transaction_services.writeIntoHistory(t);
    }

    public Transaction withdrawAmount(int accountNumber, BigDecimal amount, String description) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Balance is not enough");
        Accounts account = m_account_services.find_by_id(accountNumber);
        if (account == null)
            throw new IllegalArgumentException("Account not found");

        account.setBalance(account.getBalance().subtract(amount));
        m_account_services.updateBalance(account);

        Transaction t = new Transaction(String.valueOf(account.getCustomer_ID()), "Withdraw", amount, description);
        return m_transaction_services.writeIntoHistory(t);
    }

    public void Transfer(int fromAccountNumber, int toAccountNumber, BigDecimal amount, String description) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Balance is not enough");

        Accounts from = m_account_services.find_by_id(fromAccountNumber);
        Accounts to = m_account_services.find_by_id(fromAccountNumber);

        if (from == null || to == null)
            throw new IllegalArgumentException("Account not found");

        if (!"ACTIVE".equals(from.getStatus()) || !"ACTIVE".equals(to.getStatus()))
            throw new IllegalArgumentException("Both accounts must be active");

        if (from.getBalance().compareTo(amount) <= 0)
            throw new IllegalArgumentException("Account must have money, insufficient funds");

        if (String.valueOf(fromAccountNumber) == String.valueOf(toAccountNumber)) {
            throw new IllegalArgumentException("Can't transfer to same account");
        }

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(from.getBalance().add(amount));
        m_account_services.updateBalance(from);
        m_account_services.updateBalance(to);

        String desc = description != null ? description : "Transfer";
        Transaction debit = new Transaction(String.valueOf(from.getCustomer_ID()), "TRANSFER_OUT", amount, desc);
        debit.setRelatedAccountNumber(toAccountNumber);
        m_transaction_services.writeIntoHistory(debit);

        Transaction credit = new Transaction(String.valueOf(from.getCustomer_ID()), "TRANSFER_IN", amount, desc);
        debit.setRelatedAccountNumber(toAccountNumber);
        m_transaction_services.writeIntoHistory(debit);
    }

    public List<Transaction> getTransactionHistory(int accountNumber) {
        Accounts account = m_account_services.find_by_id(accountNumber);
        if (account == null) throw new IllegalArgumentException("Account not found");
        return m_transaction_services.findByAccountId(account.getID());
    }
}