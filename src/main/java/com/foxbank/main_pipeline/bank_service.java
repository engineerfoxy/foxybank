package com.foxbank.main_pipeline;

import com.foxbank.customer.Account_Services;

public class bank_service {
    private final Account_Services m_account_services;

    public bank_service(Account_Services accountServices) {
        this.m_account_services = accountServices;
    }
}
