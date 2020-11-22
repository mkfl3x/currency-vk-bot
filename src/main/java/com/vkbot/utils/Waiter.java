package com.vkbot.utils;

import java.util.concurrent.TimeUnit;

public class Waiter {

    private Waiter() {
    }

    public static void wait(int minutes) {
        try {
            TimeUnit.MINUTES.sleep(minutes);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
