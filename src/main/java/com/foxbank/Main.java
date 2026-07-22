package com.foxbank;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.foxbank.template.Accounts;
import com.foxbank.template.Customers;
import com.foxbank.main_pipeline.BankService;
import com.foxbank.template.Transaction;

public class Main {
    private static final BankService services = new BankService();
    private static final Scanner user_input = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("----- MENU -----\n"+
                    "1. -> Add a customer\n"+
                    "2. -> List customers\n"+
                    "3. -> Remove Customer\n"+
                    "4. -> Open an account\n"+
                    "5. -> List Accounts\n"+
                    "6. -> Deposit\n"+
                    "7. -> Withdraw\n"+
                    "8. -> Transfer\n"+
                    "9. -> Transaction history\n"+
                    "10. -> All Transaction history\n"+
                    "0. -> Quit the app");
                    System.out.print("Command: ");
            int input = user_input.nextInt();
            switch (input) {
                case 1 -> addCustomer();
                case 2 -> showAllCustomer();
                case 3 -> removeCustomer();
                case 4 -> openAnAccount();
                case 5 -> listAccounts();
                case 6 -> deposit();
                case 7 -> withdraw();
                case 8 -> transfer();
                case 9 -> transactionHistory();
                case 10 -> transactionHistoryAll();
                case 0 -> { System.out.println("Goodbye, see ya later :)"); return; }
                default -> System.out.println("invalid command you entered");
            }
        }
    }

    public static void addCustomer() {
        System.out.print("Enter customer name: "); String name = user_input.next();
        System.out.print("Enter customer age: "); int age = user_input.nextInt();
        System.out.print("Enter customer address: "); String address = user_input.next();
        System.out.print("Enter customer phone: "); String phone = user_input.next();
        Customers c = services.makeNewCustomer(name,age,address,phone);
        System.out.println("Created customer: "+c);
    }
    
    public static void showAllCustomer() {
        List<Customers> list_customers = services.findAllCustomers();
        if (list_customers.isEmpty()) System.out.println("There is not customers.");
        list_customers.forEach(System.out::println);
    }

    public static void removeCustomer() {
        System.out.print("Enter customer name: "); String name = user_input.next();
        Customers c = services.removeFromDb(name);
        System.out.println("Removed customer: "+c);
    }

    public static void openAnAccount() {
        System.out.print("Enter Customer ID: "); int customerID = user_input.nextInt();
        System.out.print("Enter Status Type: "); String accountType = user_input.next();
        Accounts a = services.makeNewAccount(customerID, accountType);
        System.out.println("Created account: "+a);
    }

    public static void listAccounts() {
        List<Accounts> list_accounts = services.findAllAccounts();
        if (list_accounts.isEmpty()) System.out.println("There is not accounts.");
        list_accounts.forEach(System.out::println);
    }

    public static void deposit() {
        System.out.print("Enter Account ID: "); int AccountID = user_input.nextInt();
        System.out.print("Amount: "); BigDecimal amount = user_input.nextBigDecimal();
        System.out.print("Description? : "); String desc = user_input.next();
        Transaction t = services.depositAmount(AccountID, amount, desc);
        System.out.println("deposited: "+t);
    }

    public static void withdraw() {
        System.out.print("Enter Account ID: "); int AccountID = user_input.nextInt();
        System.out.print("Amount: "); BigDecimal amount = user_input.nextBigDecimal();
        System.out.print("Description? : "); String desc = user_input.next();
        Transaction t = services.withdrawAmount(AccountID, amount, desc);
        System.out.println("withdrawn: "+t);
    }

    public static void transfer() {
        System.out.print("From account: "); int from = user_input.nextInt();
        System.out.print("To account: "); int to = user_input.nextInt();
        System.out.print("Amount: "); BigDecimal amt = user_input.nextBigDecimal();
        services.Transfer(from, to, amt, "Transfer");
        System.out.println("Transfer complete");
    }

    private static void transactionHistoryAll() {
        List<Transaction> list_transactions = services.findAllTransactions();
        if (list_transactions == null) System.out.println("No transaction do.");
        list_transactions.forEach(System.out::println);
    }

    private static void transactionHistory() {
        System.out.print("Account number: "); int acc = user_input.nextInt();
        List<Transaction> txs = services.getTransactionHistory(acc);
        if (txs.isEmpty()) { System.out.println("No transactions"); return; }
        txs.forEach(System.out::println);
    }
}