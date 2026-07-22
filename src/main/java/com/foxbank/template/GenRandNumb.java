package com.foxbank.template;

import java.util.concurrent.ThreadLocalRandom;

public class GenRandNumb {

    public static int generate_customer_number() {
        return ThreadLocalRandom.current().nextInt(1000,9999);
    }

    public static String generateAccountNumber() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(10000, 99999));
    }

}
