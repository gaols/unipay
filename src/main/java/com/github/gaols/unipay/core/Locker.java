package com.github.gaols.unipay.core;

public interface Locker {

    void lock(String name);

    void release(String name);

}
