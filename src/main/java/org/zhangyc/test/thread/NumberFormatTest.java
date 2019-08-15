package org.zhangyc.test.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NumberFormatTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 100; i++){
            service.submit(new NumberFormat(i % 8));
        }

        double value = 9.97981;
        String result1 = PrecisionUtil.getInstance().formatNumber(value, 4, true);
        System.out.println("value="+value+";digits="+4+";result="+result1+" true");
        String result2 = PrecisionUtil.getInstance().formatNumber(value, 4, false);
        System.out.println("value="+value+";digits="+4+";result="+result2+" false");

        double value1 = 0.00021;
        String result3 = PrecisionUtil.getInstance().formatNumber(value1, 4, true);
        System.out.println("value="+value1+";digits="+4+";result="+result3+" true");
        String result4 = PrecisionUtil.getInstance().formatNumber(value1, 4, false);
        System.out.println("value="+value1+";digits="+4+";result="+result4+" false");

        double value2 = 0.00002500;
        String result5 = PrecisionUtil.getInstance().formatNumber(value2, 8, true);
        System.out.println("value="+value2+";digits="+8+";result="+result5+" true");
        String result6 = PrecisionUtil.getInstance().formatNumber(value2, 8, false);
        System.out.println("value="+value2+";digits="+8+";result="+result6+" false");
    }


}

class NumberFormat extends Thread{

    private int digits;

    public NumberFormat(int digits){
        this.digits = digits;
    }

    @Override
    public void run() {
        Random random = new Random(10);
        for(int i = 0; i < 1000; i++){
            double value = random.nextDouble();
            String result = PrecisionUtil.getInstance().formatNumber(value, this.digits);
            System.out.println("i="+i+"; value="+value+";digits="+digits+";result="+result);

        }
    }
}
