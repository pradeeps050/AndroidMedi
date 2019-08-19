package com.orangeskill.elate.framework.application;

public class AppInstance {
    private static ElateApplication context;

    public void init(ElateApplication appContext) { // only call from App!
        context = appContext;
    }

    public  ElateApplication getContext() {
        return context;
    }

    private static AppInstance mInstance = new AppInstance();
    public static AppInstance getInstance() {
        synchronized (AppInstance.class) {
            if (mInstance == null) {
                mInstance = new AppInstance();
            }
        }
        return mInstance;
    }
}
