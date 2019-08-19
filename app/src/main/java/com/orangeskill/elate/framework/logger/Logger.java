package com.orangeskill.elate.framework.logger;

import com.orangeskill.elate.BuildConfig;

import timber.log.Timber;


public class Logger extends Timber.Tree {
    // Log when DEBUGGING is enabled
    private final static boolean DEBUG = true;

    // Verbose logging
    public static void v(String tag, String message, Object... args) {
        if (BuildConfig.DEBUG) {
            Timber.tag(tag).v(message, args);
        }
    }

    // Info logging
    public static void i(String tag, String message, Object... args) {
        if (BuildConfig.DEBUG) {
            Timber.tag(tag).i(message, args);
        }
    }

    // Debug logging
    public static void d(String tag, String message, Object... args) {
        if (BuildConfig.DEBUG) {
            Timber.tag(tag).d(message, args);
        }
    }

    // Error Logging
    public static void e(String tag, String message, Object... args) {
        if (BuildConfig.DEBUG) {
            Timber.tag(tag).e(message, args);
        }
    }

    /*
     * @deprecate please use Logger.v
     */
    @Deprecated
    public static void verbose(String Msg) {
        Timber.v(Msg);
    }

    /*
     * @deprecate please use Logger.d
     */
    @Deprecated
    public static void debug(String Msg) {
        Timber.d(Msg);
    }

    /*
     * @deprecate please use Logger.e
     */
    @Deprecated
    public static void error(String Msg) {
        Timber.e(Msg);
    }

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        Timber.tag(tag).log(priority, message, t);
    }
}
