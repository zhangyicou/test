package org.zhangyc.test.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 16/7/20.
 */
public class Test {
    public static void main(String[] args){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date checkInDate = sdf.parse("2016-07-22");
            System.out.println(String.valueOf(checkInDate.getTime() / 1000));
            Date checkOutDate = sdf.parse("2016-07-26");
            System.out.println(String.valueOf(checkOutDate.getTime() / 1000));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
