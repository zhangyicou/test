package org.zhangyc.test.bytes;

/**
 * @Author: yichu.zhang
 * @Date: 2020-02-22 18:04
 */
public class Type2BytesLenghtTest {
    public static void main(String[] args) {
        Byte a = 1;
        Boolean b = true;
        Short c = 1;
        Character d = 'c';
        Integer e = 1;
        Float f =1f;
        Long g = System.currentTimeMillis();
        Double h = 1d;
        String i = "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii";
        System.out.println("String.lenght = "+i.getBytes().length);
    }
}
