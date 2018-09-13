package org.zhangyc.test.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by zhang on 2018/9/7.
 */
public class DynamicProxy<T> implements InvocationHandler {

    private T obj;

    public DynamicProxy(T obj) {
        this.obj = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //    当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        Object result = method.invoke(this.obj, args);
        return result;
    }
}
