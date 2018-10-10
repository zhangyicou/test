package org.zhangyc.test.excel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by zhang on 2018/6/5.
 */
public abstract class ExeclManager {

     protected CellStyle getStyle(Workbook workbook) {
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
