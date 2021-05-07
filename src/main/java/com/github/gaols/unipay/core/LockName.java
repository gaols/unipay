package com.github.gaols.unipay.core;

import java.nio.charset.Charset;
import java.util.Arrays;

public class LockName {

    private byte[] name;

    public LockName(String name) {
        this.name = name.getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(name);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LockName) {
            return Arrays.equals(name, ((LockName) obj).name);
        }
        return obj instanceof String && toString().equals(obj);
    }

}
