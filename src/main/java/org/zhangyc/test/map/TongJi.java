package org.zhangyc.test.map;

import java.util.Hashtable;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhang on 2018/9/13.
 */
public class TongJi {
    private String date;
    private String desctription;

    public static void main(String[] args){
        TreeMap<String, TongJi> map = new TreeMap<String, TongJi>();

        TongJi tj1 = new TongJi();
        tj1.setDate("2016-06-16");
        tj1.setDesctription("20160612");
        map.put(tj1.getDate(), tj1);

        TongJi tj2 = new TongJi();
        tj2.setDate("2016-06-15");
        tj2.setDesctription("20160613");
        map.put(tj2.getDate(), tj2);

        TongJi tj3 = new TongJi();
        tj3.setDate("2016-06-14");
        tj3.setDesctription("20160614");
        map.put(tj3.getDate(), tj3);

        TongJi tj4 = new TongJi();
        tj4.setDate("2016-06-13");
        tj4.setDesctription("20160615");
        map.put(tj4.getDate(), tj4);

        TongJi tj5 = new TongJi();
        tj5.setDate("2016-06-12");
        tj5.setDesctription("20160616");
        map.put(tj5.getDate(), tj5);

        for(String key : map.keySet()){
            System.out.println(key+":"+map.get(key).getDesctription());
        }
    }

    ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesctription() {
        return desctription;
    }

    public void setDesctription(String desctription) {
        this.desctription = desctription;
    }
}
