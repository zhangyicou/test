package org.zhangyc.test.list.exce;

/**
 * Created by oker on 2019/1/14.
 */
public class ForeachException {
    public static void main(String[] args) {
        for(int i = 0; i < 10; i++){
            System.out.println("i="+i);
            try {
                exce(i);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    private static void exce(int i) throws Exception {
        if(i == 5) {
            throw new Exception("Exception");
        }
    }
}
