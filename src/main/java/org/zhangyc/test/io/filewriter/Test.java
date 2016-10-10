package org.zhangyc.test.io.filewriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by user on 16/8/9.
 */
public class Test {
    public static void main(String[] args){
        FileWriter writer = null;
        try {
            File file = new File("/Users/user/Documents/zhangyicou/shopper/inc/dcprice/201608111100");
            if(!file.exists())
                file.mkdirs();
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            writer = new FileWriter("/Users/user/Documents/zhangyicou/shopper/inc/dcprice/201608111100/FileWriter.txt", true);
            for(int i =0; i < 10; i++) {
                writer.write("content."+i+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String filePath = "logs/dc-core";
        System.out.println(filePath.substring(filePath.indexOf("logs")+5));
    }
}
