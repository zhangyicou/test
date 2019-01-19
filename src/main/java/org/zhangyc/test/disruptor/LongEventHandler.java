package org.zhangyc.test.disruptor;

import com.lmax.disruptor.EventHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyicou on 2019/1/19.
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    String filePath = "D:/logs/disruptor.txt";
    private File file = null;
    private FileWriter fw = null;
    private BufferedWriter bw = null;

    public LongEventHandler(){
        try {
            file = new File(filePath);
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        bw.write(String.valueOf(System.currentTimeMillis())+"\n");
    }
}