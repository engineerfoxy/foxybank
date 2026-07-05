package com.foxbank;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.foxbank.template.Accounts;
import com.foxbank.template.Customers;
import com.foxbank.main_pipeline.bank_service;

public class Main {
    static Scanner user_input = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            System.out.println("----- MENU -----\n"+
                    "1. -> Add a customer\n"+
                    "2. -> List customers\n"+
                    "3. -> Remove Customer\n"+
                    "4. -> Open an account\n"+
                    "5. -> Close the Account\n"+
                    "6. -> Deposit\n"+
                    "7. -> Withdraw\n"+
                    "0. -> Quit the app");
                    System.out.print("Command: ");
            int input = user_input.nextInt();
            switch (input) {
                case 1 -> add_customer();
                case 2 -> list_customer();
                case 3 -> remove_customer();
                case 4 -> open_an_account();
                case 5 -> close_the_account();
                case 6 -> deposit();
                case 7 -> withdraw();
                case 0 -> { System.out.println("Goodbye, see ya later :)"); return; }
                default -> System.out.println("invalid command you entered");
            }
        }
    }

    public static void add_customer() {
        System.out.print("Enter customer name: "); String name = user_input.next();
        System.out.print("Enter customer age: "); int age = user_input.nextInt();
        System.out.print("Enter customer address: "); String address = user_input.next();
        System.out.print("Enter customer phone: "); String phone = user_input.next();
        Customers c = new Customers(name,age,address,phone);
        System.out.println("Created: "+c);
    }
    
    public static void list_customer() {
      
    }
    
    public static void remove_customer() {
      
    }

    public static void open_an_account() {

    }

    public static void close_the_account() {

    }

    public static void deposit() {

    }

    public static void withdraw() {

    }
}