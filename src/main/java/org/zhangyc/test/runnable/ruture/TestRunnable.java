package org.zhangyc.test.runnable.ruture;

/**
 * Created by user on 16/7/18.
 */
public class TestRunnable implements Runnable {
    public void run() {
        throw new RuntimeException("RuntimeException....");
    }
}
