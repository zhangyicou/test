package org.zhangyc.test.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by zhang on 2018/9/7.
 */
public class Test {
    public static void main(String[] args){
        IEngineer engineer = new JavaEngineerImpl();
        InvocationHandler enginnerHandler = new DynamicProxy<IEngineer>(engineer);

        IEngineer javaEngineerProxy = (IEngineer)Proxy.newProxyInstance(IEngineer.class.getClassLoader(), new Class<?>[]{IEngineer.class}, enginnerHandler);

        javaEngineerProxy.coding();

        javaEngineerProxy.testing();

        javaEngineerProxy.online();
    }
}
