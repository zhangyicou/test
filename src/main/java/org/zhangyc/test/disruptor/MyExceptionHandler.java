package org.zhangyc.test.disruptor;

import com.lmax.disruptor.ExceptionHandler;

public class MyExceptionHandler implements ExceptionHandler {
    @Override
    public void handleEventException(Throwable ex, long sequence, Object event) {
        System.out.println("handleEventException---------");
        ex.printStackTrace();
    }

    @Override
    public void handleOnStartException(Throwable ex) {
        System.out.println("handleOnStartException---------");
        ex.printStackTrace();
    }

    @Override
    public void handleOnShutdownException(Throwable ex) {
        System.out.println("handleOnStartException---------");
        ex.printStackTrace();
    }
}
