package com.github.gaols.unipay.core;

import java.util.concurrent.locks.ReentrantLock;

public class SimpleLocker implements Locker {

    private final ReentrantLock locker;
    private static final int MAX_LOCKS = 1000;
    private final ReentrantLock[] locks = new ReentrantLock[MAX_LOCKS];

    public SimpleLocker() {
        this.locker = new ReentrantLock();
    }

    @Override
    public void lock(String name) {
        int slot = new LockName(name).hashCode() % MAX_LOCKS;
        try {
            locker.lock();
            if (locks[slot] == null) {
                locks[slot] = new ReentrantLock();
            }
        } finally {
            locker.unlock();
        }
        locks[slot].lock();
    }

    @Override
    public void release(String name) {
        try {
            locker.lock();
            int slot = new LockName(name).hashCode() % MAX_LOCKS;
            locks[slot].unlock();
        } finally {
            locker.unlock();
        }
    }
}
