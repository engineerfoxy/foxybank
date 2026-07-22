package com.foxbank.main_pipeline;

import com.foxbank.customer.TransactionService;
import com.foxbank.template.Transaction;
import com.foxbank.template.GenRandNumb;
import com.foxbank.customer.AccountServices;
import com.foxbank.customer.CustomerServices;
import com.foxbank.template.Accounts;
import com.foxbank.template.Customers;

import java.math.BigDecimal;
import java.util.List;

public class BankService {
    private final AccountServices m_account_services;
    private final CustomerServices m_customer_services;
    private final TransactionService m_transaction_services;

    public BankService() {
        this.m_customer_services = new CustomerServices();
        this.m_account_services = new AccountServices();
        m_transaction_services = new TransactionService();
    }

    public Customers makeNewCustomer(String name, int age, String address, String phone) {
        return m_customer_services.Save(new Customers(name, age, address, phone));
    }

    public Customers removeFromDb(String name) {
        return m_customer_services.Remove(new Customers(name));
    }

    public Accounts makeNewAccount(int customerID, String accountType) {
        Customers customers = m_customer_services.findAllInDbById(customerID);
        if (customers == null) throw new RuntimeException("Customer not found!");
        String generate_random_number = GenRandNumb.generateAccountNumber();
        return m_account_services.Save(new Accounts(customerID, generate_random_number, accountType));
    }

    public List<Customers> findAllCustomers() {
        return m_customer_services.findAllInDb();
    }

    public Accounts toggle_active_account(String account_ID) {
        Accounts accounts = m_account_services.ActiveDisableAccount(new Accounts(account_ID));
        if (accounts == null) {
            throw new RuntimeException("account wasn't in database");
        }
        return accounts;
    }

    public List<Transaction> findAllTransactions() { return m_transaction_services.listAll(); }

    public List<Accounts> findAllAccounts() { return m_account_services.listingAll(); }

    public Transaction depositAmount(int accountNumber, BigDecimal amount, String description) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Balance is not enough");
        Accounts account = m_account_services.FindById(accountNumber);
        if (account == null)
            throw new IllegalArgumentException("Account not found");

        account.setBalance(account.getBalance().add(amount));
        m_account_services.updateBalance(account);

        Transaction t = new Transaction(String.valueOf(account.getCustomerID()), "Deposit", amount, description);
        return m_transaction_services.writeIntoHistory(t);
    }

    public Transaction withdrawAmount(int accountNumber, BigDecimal amount, String description) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Balance is not enough");
        Accounts account = m_account_services.FindById(accountNumber);
        if (account == null)
            throw new IllegalArgumentException("Account not found");

        account.setBalance(account.getBalance().subtract(amount));
        m_account_services.updateBalance(account);

        Transaction t = new Transaction(String.valueOf(account.getCustomerID()), "Withdraw", amount, description);
        return m_transaction_services.writeIntoHistory(t);
    }

    public void Transfer(int fromAccountNumber, int toAccountNumber, BigDecimal amount, String description) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Balance is not enough");

        Accounts from = m_account_services.FindById(fromAccountNumber);
        Accounts to = m_account_services.FindById(fromAccountNumber);

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
        Transaction debit = new Transaction(String.valueOf(from.getCustomerID()), "TRANSFER_OUT", amount, desc);
        debit.setRelatedAccountNumber(toAccountNumber);
        m_transaction_services.writeIntoHistory(debit);

        Transaction credit = new Transaction(String.valueOf(from.getCustomerID()), "TRANSFER_IN", amount, desc);
        credit.setRelatedAccountNumber(toAccountNumber);
        m_transaction_services.writeIntoHistory(debit);
    }

    public List<Transaction> getTransactionHistory(int accountNumber) {
        Accounts account = m_account_services.FindById(accountNumber);
        if (account == null) throw new IllegalArgumentException("Account not found");
        return m_transaction_services.findByAccountId(account.getID());
    }
}