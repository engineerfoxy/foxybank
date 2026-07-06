package com.foxbank.main_pipeline;

import com.foxbank.template.gen_rand_numb;
import com.foxbank.customer.Account_Services;
import com.foxbank.customer.Customer_Services;
import com.foxbank.template.Accounts;
import com.foxbank.template.Customers;

import java.util.List;

public class bank_service {
    private final Account_Services m_account_services;
    private final Customer_Services m_customer_services;

    public bank_service() {
        this.m_customer_services = new Customer_Services();
        this.m_account_services = new Account_Services();
    }

    public Customers make_new_customer(String name, int age, String address, String phone) {
        return m_customer_services.save(new Customers(name, age, address, phone));
    }

    public Customers remove_from_db(String name) {
        return m_customer_services.remove(new Customers(name));
    }

    public Accounts make_new_account(int customerID, String accountType) {
        Customers customers = Customer_Services.find_all_in_db_by_id(customerID);
        if (customers == null) throw new RuntimeException("Customer not found!");
        return m_account_services.save(new Accounts(customerID, gen_rand_numb.generate_account_number(), accountType));
    }

    public List<Customers> find_all_customers() {
        return m_customer_services.find_all_in_db();
    }
}
