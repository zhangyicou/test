package org.zhangyc.test.excel.jdgift;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zhangyc.test.excel.EncryptUtils;
import org.zhangyc.test.excel.ExeclManager;

import javax.servlet.ServletOutputStream;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhang on 2018/6/5.
 */
public class JDGiftExeclManagerImpl extends ExeclManager {

    public static void main(String[] args){
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            String projectPath = System.getProperty("user.dir");
            String filePath = projectPath.concat("/src/main/java/org/zhangyc/test/excel/jdgift/WKLC-2018-09-19_11_09_18.txt");
            String excelPath = projectPath.concat("/src/main/java/org/zhangyc/test/excel/jdgift/InvalidCard.xlsx");

            File jdGiftFile = new File(filePath);
            inputStream = new FileInputStream(jdGiftFile);
            sc = new Scanner(inputStream, "UTF-8");
            int count = 0;

            List<JDGiftInfo> giftList = new ArrayList<JDGiftInfo>();

            JDGiftExeclManagerImpl execlManagerImpl = new JDGiftExeclManagerImpl();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String outFileName = "InvalidCard-".concat(sdf.format(new Date()));

            int index = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if(count > 0){
                    if(line != null){
                        String[] str = line.split("\t");
                        if(str.length == 7 &&  !"NULL".equals(str[4]) && (str[4].indexOf("-") < 0)){
                            //System.out.println(str[0] + "-->" + str[1]+ "-->" + str[2]+ "-->" + str[3]+ "-->" + str[4]+ "-->" + str[5]+ "-->" + str[6]);
                            str[4] = EncryptUtils.decryptPwd(str[4]);
                        }

                        JDGiftInfo giftInfo = new JDGiftInfo();
                        giftInfo.setUserId(str[0]);
                        giftInfo.setTaskSign(str[1]);
                        giftInfo.setGiftName(str[2]);
                        giftInfo.setCardNo(str[3]);
                        giftInfo.setExpressNo(str[4]);
                        giftInfo.setCreateTime(str[5]);
                        giftInfo.setGiftValidTime(str[6]);
                        giftList.add(giftInfo);


                        if(giftList.size() >= 1000){

                            execlManagerImpl.getMainCardExcel(index, giftList, excelPath, outFileName);
                            index++;
                            giftList = new ArrayList<JDGiftInfo>();
                        }
                    }
                }

                count++;
            }

            if(!giftList.isEmpty()){
                execlManagerImpl.getMainCardExcel(index, giftList, excelPath, outFileName);
            }


            System.out.println("count="+count);
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sc != null) {
                sc.close();
            }
        }
    }

    /**
     * list 存放查询返回得结果列表
     * sheetname excel模板中sheet得名字
     * out 输出流对象
     */
     public void getMainCardExcel(int index, List<JDGiftInfo> giftList, String filepath, String outFileName) throws FileNotFoundException, IOException {
         System.out.println("index=" + index);
        //  读取工作簿
         FileInputStream fileInputStream = null;
         if (index == 0) {
             fileInputStream = new FileInputStream(filepath);
         } else{
             //  读取工作簿
             fileInputStream = new FileInputStream(filepath.replace("InvalidCard", outFileName));
         }
         Workbook workbook =new XSSFWorkbook(fileInputStream);
         try{

             //读取工作表
             Sheet sheet = workbook.getSheetAt(0);
             Row row;
             Cell cell = null;
             CellStyle style = this.getStyle(workbook);
             for(int i =1; i <= giftList.size();i++)
             {
                 //从查询结果列表中获取对象
                 JDGiftInfo enroll = giftList.get(i-1);
                 //该行以前得部分从模板中取得;
                 row = sheet.createRow(index * 1000  + i);

                 cell = row.createCell(0, CellType.STRING);
                 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                 cell.setCellValue(enroll.getUserId());
                 cell.setCellStyle(style);

                 cell = row.createCell(1, CellType.STRING);
                 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                 cell.setCellValue(enroll.getTaskSign());
                 cell.setCellStyle(style);

                 cell = row.createCell(2, CellType.STRING);
                 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                 cell.setCellValue(enroll.getGiftName());
                 cell.setCellStyle(style);

                 cell = row.createCell(3, CellType.STRING);
                 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                 cell.setCellValue(enroll.getCardNo());
                 cell.setCellStyle(style);

                 cell = row.createCell(4, CellType.STRING);
                 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                 cell.setCellValue(enroll.getExpressNo());
                 cell.setCellStyle(style);

                 cell = row.createCell(5, CellType.STRING);
                 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                 cell.setCellValue(enroll.getCreateTime());
                 cell.setCellStyle(style);

                 cell = row.createCell(6, CellType.STRING);
                 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                 cell.setCellValue(enroll.getGiftValidTime());
                 cell.setCellStyle(style);
             }



             OutputStream out = new FileOutputStream(filepath.replace("InvalidCard", outFileName));
             workbook.write(out);
             out.flush();
             out.close();
         }
         catch(Exception e) {
            e.printStackTrace();
         }
     }

     public CellStyle getStyle(Workbook workbook) {
         //     设置字体;
         Font font = workbook.createFont();
         //设置字体大小;
         font.setFontHeightInPoints((short)9);
         //设置字体名字;
         font.setFontName("Courier New");
         //font.setItalic(true);
         //font.setStrikeout(true);
         //     设置样式;
         CellStyle style = workbook.createCellStyle();
         //设置底边框;
         style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
         //设置底边框颜色;
         style.setBottomBorderColor(HSSFColor.BLACK.index);
         //设置左边框;
         style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
         //设置左边框颜色;
         style.setLeftBorderColor(HSSFColor.BLACK.index);
         //设置右边框;
         style.setBorderRight(HSSFCellStyle.BORDER_THIN);
         //设置右边框颜色;
         style.setRightBorderColor(HSSFColor.BLACK.index);
         //设置顶边框;
         style.setBorderTop(HSSFCellStyle.BORDER_THIN);
         //设置顶边框颜色;
         style.setTopBorderColor(HSSFColor.BLACK.index);
         //在样式用应用设置的字体;
         style.setFont(font);
         //设置自动换行;
         style.setWrapText(false);
         //设置水平对齐的样式为居中对齐;
         style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
         //设置垂直对齐的样式为居中对齐;
         style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
         return style;
     }
}
