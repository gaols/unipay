package com.github.gaols.unipay.core;

import java.util.concurrent.locks.ReentrantLock;

public class SimpleLocker implements Locker {

    private final ReentrantLock locker;

    public SimpleLocker() {
        this.locker = new ReentrantLock();
    }

    @Override
    public void lock() {
        locker.lock();
    }

    @Override
    public void release() {
        locker.unlock();
    }
}
