package com.foxbank.DBmanage;

import java.util.Random;

public class gen_rand_numb {

    Random random = new Random();

    public int gen_rand_ID_account() {
        return random.nextInt(999999) + 100000;
    }

    public int gen_rand_ID_customer() {
        return random.nextInt(99999) + 10000;
    }

}
