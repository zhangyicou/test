package org.zhangyc.test.date;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Created by user on 16/7/20.
 */
public class Test {
    public static void main(String[] args){
        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date checkInDate = sdf.parse("2016-07-22");
//            System.out.println(String.valueOf(checkInDate.getTime() / 1000));
//            Date checkOutDate = sdf.parse("2016-07-26");
//            System.out.println(String.valueOf(checkOutDate.getTime() / 1000));
//

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
            String date1 = "2019-10-09 23:26:05.471";
            String date2 = "2019-10-09 23:26:05.514";
            String date3 = "2019-10-09 23:26:05.54";

//            System.out.println(sdf.format(sdf.parse(date1)));
//            System.out.println(sdf.format(sdf.parse(date2)));
//            System.out.println(sdf.format(sdf.parse(date3)));


            System.out.println(sdf.parse(date3).getTime());
            System.out.println(DateUtil.timeToString(sdf.parse(date1), 12));
            System.out.println(DateUtil.timeToString(sdf.parse(date2), 12));
            System.out.println(DateUtil.timeToString(sdf.parse(date3), 12));

            LocalDateTime localDateTime = LocalDateTime.parse("2019-10-09 23:26:05.54", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS"));
            System.out.println(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
