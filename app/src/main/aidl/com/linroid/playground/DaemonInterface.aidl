// DaemonInterface.aidl
package com.linroid.playground;

import com.linroid.playground.SomeCallback;

// Declare any non-default types here with import statements

interface DaemonInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void ping(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void setCallback(SomeCallback callback);

    void releaseCallback(boolean gc);
}