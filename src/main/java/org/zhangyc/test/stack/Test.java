package org.zhangyc.test.stack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Created by user on 16/9/19.
 */
public class Test {
    public static void main(String[] args){
        String str = "I am a student";
        statck(str);

        System.out.println(reverse(str));

        reverse1(str);

        reverse2(str);

        reverse3(str);

        reverse4(str);
    }

    public static void reverse1(String old) {
        String result = "";
        for (int i = old.length() - 1; i >= 0; i--) {
            result += String.valueOf(old.charAt(i));
        }

        System.out.println("1:"+result);
    }

    public static void reverse2(String old) {
        String result = "";
        for (int i = 0; i < old.length(); i++) {
            result = old.charAt(i) + result;
        }

        System.out.println("2:"+result);
    }

    public static void reverse3(String old) {
        String result = "";
        List<String> olds = Arrays.asList(old.split(""));
        Collections.reverse(olds);

        for (String s : olds) {
            result += s;
        }
        System.out.println("3:"+result);
    }

    public static void reverse4(String old) {
        char[] chars = old.toCharArray();
        int n = chars.length - 1;

        for (int i = 0; i <= n / 2; i++) {
            char temp = chars[i];

            chars[i] = chars[n - i];
            chars[n - i] = temp;
        }

        System.out.println("4:"+new String(chars));
    }

    public static String reverse(String s) {
        int length = s.length();
        if (length <= 1)
            return s;
        String left = s.substring(0, length / 2);
        String right = s.substring(length / 2, length);
        return reverse(right) + reverse(left);
    }

    private static void statck(String str){
        System.out.println(str);
        String[] strs = str.split(" ");
        Stack<String> stack = new Stack<String>();
        for(int i = 0; i < strs.length; i++){
            stack.push(strs[i]);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < strs.length; i++){
            sb.append(stack.pop());
            sb.append(" ");
        }

        System.out.println(sb.toString());
        System.out.println(sb.reverse());
    }
}
