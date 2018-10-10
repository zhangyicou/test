package org.zhangyc.test.file;

import com.alibaba.fastjson.JSON;
import com.sun.deploy.util.StringUtils;
import org.zhangyc.test.excel.EncryptUtils;
import org.zhangyc.test.excel.jdgift.JDGiftExeclManagerImpl;
import org.zhangyc.test.excel.jdgift.JDGiftInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhang on 2018/9/25.
 */
public class FileMerge {
    public static void main(String[] args) {
        FileInputStream recordIS = null;
        Scanner recordScanner = null;
        FileWriter userBcardfw = null;
        FileWriter recordfw = null;
        try {
            String projectPath = System.getProperty("user.dir");
            String recordFilePath = projectPath.concat("/src/main/java/org/zhangyc/test/file/record_0");

            String userBcardPath = projectPath.concat("/src/main/java/org/zhangyc/test/file/wk_user_bcard.sql");
            String recordPath = projectPath.concat("/src/main/java/org/zhangyc/test/file/wk_record.sql");

            File recordFile = new File(recordFilePath);
            recordIS = new FileInputStream(recordFile);
            recordScanner = new Scanner(recordIS, "UTF-8");

            JDGiftExeclManagerImpl execlManagerImpl = new JDGiftExeclManagerImpl();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String outFileName = "InvalidCard-".concat(sdf.format(new Date()));

            Map<String, Bank> bankMap = new HashMap<String, Bank>(500);

            List<StringBuilder> userBcardList = new ArrayList<StringBuilder>();
            List<StringBuilder> recordList = new ArrayList<StringBuilder>();
            while (recordScanner.hasNextLine()) {
                String line = recordScanner.nextLine();
                if(line == null || line.equals("")) {
                    continue;
                }

                Bank bank = JSON.parseObject(line, Bank.class);
                StringBuilder userBcardSB = new StringBuilder("update wk_user_bcard set ");
                userBcardSB.append(" bank_code = '").append(bank.getBankCode()).append("'");
                userBcardSB.append(" , bank_name = '").append(bank.getBankName()).append("'");
                userBcardSB.append(" where user_id = ").append(bank.getUserId());
                userBcardSB.append(" and card_no = '").append(bank.getCardNo()).append("'");
                userBcardSB.append(" and bank_name = '").append(bank.getBankNameError()).append("'");
                userBcardSB.append(" and bank_code = '").append(bank.getBankCodeError()).append("';");

                userBcardList.add(userBcardSB);


                StringBuilder recordSB = new StringBuilder("update wk_record set ");
                recordSB.append(" bank_code = '").append(bank.getBankCode()).append("'");
                recordSB.append(" , bank_name = '").append(bank.getBankName()).append("'");
                recordSB.append(" where user_id = ").append(bank.getUserId());
                recordSB.append(" and card_no = '").append(bank.getCardNo()).append("'");
                recordSB.append(" and bank_name = '").append(bank.getBankNameError()).append("'");
                recordSB.append(" and bank_code = '").append(bank.getBankCodeError()).append("';");
                recordList.add(recordSB);
            }

            userBcardfw =  new FileWriter(userBcardPath, true);

            for(StringBuilder sb : userBcardList) {
                userBcardfw.write(sb.toString()+"\n");
            }
            userBcardfw.flush();

            recordfw =  new FileWriter(recordPath, true);

            for(StringBuilder sb : recordList) {
                recordfw.write(sb.toString()+"\n");
            }
            recordfw.flush();

            if (recordScanner.ioException() != null) {
                throw recordScanner.ioException();
            }
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }finally {
            if (recordIS != null) {
                try {
                    recordIS.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (recordScanner != null) {
                recordScanner.close();
            }
            if(userBcardfw != null){
                try {
                    userBcardfw.close();
                }catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

            if(recordfw != null){
                try {
                    recordfw.close();
                }catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
