package com.foxbank;

import java.util.Scanner;

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
            char input = user_input.next().charAt(0);
            switch (input) {
                case '1':
                    System.out.println("Add a customer");
                    add_customer();
                    break;
                case '2':
                    System.out.println("List customers");
                    list_customer();
                    break;
                case '3':
                    System.out.println("Remove Customer");
                    remove_customer();
                    break;
                case '4':
                    System.out.println("Open an account");
                    break;
                case '5':
                    System.out.println("Close the Account");
                    break;
                case '6':
                    System.out.println("Deposit");
                    break;
                case '7':
                    System.out.println("Withdraw");
                    break;
                case '0': {
                    System.out.println("Goodbye, see ya later :)");
                    return;
                }
                default:
                    System.out.println("invalid command you entered");

            }
        }
    }
    
    public static void add_customer() {

    }
    
    public static void list_customer() {
      
    }
    
    public static void remove_customer() {
      
    }
}