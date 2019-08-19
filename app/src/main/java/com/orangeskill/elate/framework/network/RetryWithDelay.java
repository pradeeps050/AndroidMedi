package com.orangeskill.elate.framework.network;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;


public class RetryWithDelay implements
        Func1<Observable<? extends Throwable>, Observable<?>> {

    private final int maxRetries;
    private final int retryDelaySecs;
    private int retryCount;

    /**
     * Retry the request given number of Times with Exponential
     * @param maxRetries - Number of retry attempts
     * @param retryDelayInSecs - Minimum retry delay in Seconds
     */
    public RetryWithDelay(int maxRetries, int retryDelayInSecs) {
        this.maxRetries = maxRetries;
        this.retryDelaySecs = retryDelayInSecs;
        this.retryCount = 0;
    }

    @Override
    public Observable<?> call(Observable<? extends Throwable> attempts) {
        return attempts
                .flatMap((Func1<Throwable, Observable<?>>) throwable -> {
                    if (++retryCount <= maxRetries) {
                        // When this Observable calls onNext, the original Observable will be retried (i.e. re-subscribed).
                        long delay = (long) Math.pow(retryDelaySecs, retryCount);
                        return Observable.timer(delay,
                                TimeUnit.SECONDS);
                    }
                    // Max retries hit. Just pass the error along.
                    return Observable.error(throwable);
                });
    }
}