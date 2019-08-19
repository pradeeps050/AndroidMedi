package com.orangeskill.elate.framework.ui.activity;


public enum AppScreens {
    BILLING_SUMMARY(2),
    HOME(3);



    int value;

    AppScreens(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}