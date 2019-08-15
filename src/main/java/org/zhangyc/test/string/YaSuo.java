package org.zhangyc.test.string;

import java.util.Scanner;

public class YaSuo {
    public static void main(String [] args){
        Scanner input=new Scanner(System.in);
        String str = input.nextLine();
        StringBuilder sb = new StringBuilder();
        char c =str.charAt(0);
        int count = 1;
        for(int i = 1; i <str.length(); i++){
            char s = str.charAt(i);
            if(s == c){
                count++;
            }else{
                if(count > 1){
                    sb.append(c);
                    sb.append(count);
                    count = 1;
                }else{
                    sb.append(c);
                }
            }
            c = s;
        }
        sb.append(c);
        if(count > 1)
            sb.append(count);
        System.out.println(sb.toString());
    }
}
