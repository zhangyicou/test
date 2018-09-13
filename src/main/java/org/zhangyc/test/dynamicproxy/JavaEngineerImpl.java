package org.zhangyc.test.dynamicproxy;

/**
 * Created by zhang on 2018/9/7.
 */
public class JavaEngineerImpl implements IEngineer {

    public JavaEngineerImpl(){
        System.out.println("Hello, I'm a java engineer.");
    }

    public void coding() {
        System.out.println("I'm coding my java project.....");
    }

    public void testing() {
        System.out.println("I'm testing my java project.....");
    }

    public void online() {
        System.out.println("I'm online my java project.....");
    }
}
