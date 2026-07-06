package com.foxbank.template;

import java.util.concurrent.ThreadLocalRandom;

public class gen_rand_numb {

    public static int generate_customer_number() {
        return ThreadLocalRandom.current().nextInt(1000,9999);
    }

    public static String generate_account_number() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(10000, 99999));
    }

}
