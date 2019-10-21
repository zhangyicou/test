package org.zhangyc.test.date;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class StringUtil {

    public static final String EMPTY = "";
    public static Double toDouble(String s) {
        return toDouble(s, null);
    }

    /***
     * 转换object类型参数
     * @param s
     * @param defaultValue
     * @return
     */
    public static Double toDouble(Object s, Double defaultValue) {
        if (s == null) {
            return defaultValue;
        }
        return toDouble(s.toString(), defaultValue);
    }

    public static Double toDouble(String s, Double defaultValue) {

        if (s == null || "null".equalsIgnoreCase(s.trim()) || "".equals(s.trim())) { return defaultValue; }
        try {
            Double d = Double.parseDouble(s.trim());
            if (Objects.equals(d, Double.POSITIVE_INFINITY)) {
                return defaultValue;
            }
            if (Objects.equals(d, Double.NEGATIVE_INFINITY)) {
                return defaultValue;
            }
            if (Objects.equals(d, Double.NaN)) {
                return defaultValue;
            }
            return d;
        } catch (Exception e) {
            //log.error("", e);
            return defaultValue;
        }
    }

    public static Integer toInteger(String s) {
        return toInteger(s, 0);
    }

    public static Integer toInteger(String s, Integer defaultValue) {
        if (s == null || "".equals(s.trim()) || !s.matches("^[-+]?[0-9]+$")) { return defaultValue; }
        try {
            return Integer.parseInt(s.trim());
        } catch (Exception e) {
            //log.error("", e);
            return defaultValue;
        }
    }

    public static Long toLong(String s) {
        return toLong(s, null);
    }

    public static Long toLong(String s, Long defaultValue) {
        if (s == null || "null".equals(s) || "".equals(s.trim())) { return defaultValue; }
        try {
            return Long.parseLong(s.trim());
        } catch (Exception e) {
            //log.error("long parse exception", e);
            return defaultValue;
        }
    }

    public static boolean isInt(String id) {
        if (id == null) { return false; }
        try {
            Integer.parseInt(id.trim());
        } catch (Exception e) {
            //log.error("", e);
            return false;
        }
        return true;
    }

    public static boolean isEmpty(String str) {
        if (str == null) { return true; }
        String tempStr = str.trim();
        if (tempStr.length() == 0) { return true; }
        return tempStr.equals("null");
    }

    public static String replace(String str, String pattern, String replace) {
        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer();

        while ((e = str.indexOf(pattern, s)) >= 0) {
            result.append(str, s, e);
            result.append(replace);
            s = e + pattern.length();
        }
        result.append(str.substring(s));
        return result.toString();
    }


    /**
     * 字符串替换，将 source 中的 oldString 全部换成 newString
     *
     * @param source    源字符串
     * @param oldString 老的字符串
     * @param newString 新的字符串
     * @return 替换后的字符串
     */
    public static String replaceStr(String source, String oldString, String newString) {
        StringBuffer output = new StringBuffer();

        int lengthOfSource = source.length();   // 源字符串长度
        int lengthOfOld = oldString.length();   // 老字符串长度

        int posStart = 0;   // 开始搜索位置
        int pos;            // 搜索到老字符串的位置

        String lower_s = source.toLowerCase();        //不区分大小写
        String lower_o = oldString.toLowerCase();

        while ((pos = lower_s.indexOf(lower_o, posStart)) >= 0) {
            output.append(source, posStart, pos);

            output.append(newString);
            posStart = pos + lengthOfOld;
        }

        if (posStart < lengthOfSource) {
            output.append(source.substring(posStart));
        }

        return output.toString();
    }



    public static long ipToLong(String strIP) {
        try {
            if (strIP == null || strIP.length() == 0) {
                return 0L;
            }
            long[] ip = new long[4];
            int position1 = strIP.indexOf(".");
            int position2 = strIP.indexOf(".", position1 + 1);
            int position3 = strIP.indexOf(".", position2 + 1);
            ip[0] = Long.parseLong(strIP.substring(0, position1));
            ip[1] = Long.parseLong(strIP.substring(position1 + 1, position2));
            ip[2] = Long.parseLong(strIP.substring(position2 + 1, position3));
            ip[3] = Long.parseLong(strIP.substring(position3 + 1));
            return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
        } catch (Exception ex) {
            return 0L;
        }
    }

    /**
     * 是否为中国手机号码<br>
     *
     * @param mobiles
     * @return
     * @date 2016-4-18
     */
    public static boolean isChineseMobile(String mobiles) {
        if (isEmpty(mobiles)) {
            return false;
        }
        String regex1 = "^1[3|4|5|7|8][0-9]{9}$";
        Pattern p1 = Pattern.compile(regex1);
        Matcher m1 = p1.matcher(mobiles);
        return m1.matches();
    }

    public static String doubleDecimalCny(double value) {
        if (value == 0) {
            return "0";
        } else {
            String result = value + "";
            if (value % 1.0 == 0) {
                result = (long)value + "";
            }
            if (result.indexOf("E") > -1) {
                NumberFormat formate = NumberFormat.getNumberInstance();
                formate.setMaximumFractionDigits(8);
                formate.setMaximumIntegerDigits(8);
                result = formate.format(value);
            }
            int dotIndex = result.indexOf(".");
            if (dotIndex > -1) {
                String part1 = result.substring(0, dotIndex);
                if (result.length() > part1.length() + 3) {
                    result = part1 + result.substring(dotIndex, part1.length() + 3);
                }
                return result;
            } else {
                return result;
            }
        }
    }

    public static String doubleDecimalBtc(double value) {
        if (value == 0) {
            return "0";
        } else {
            double result = value;
            String resultStr = result + "";
            if (!StringUtil.isEmpty(resultStr) && resultStr.indexOf(".") > -1) {
                String[] resultStrs = resultStr.split("[.]");
                if (resultStrs != null && resultStrs.length > 1 && resultStrs[1].length() > 6) {
                    result = Math.floor(value * 1000000) / 1000000.0;
                    resultStr = result + "";
                }
            }
            if (result % 1.0 == 0) {
                resultStr = (long)result + "";
            }
            if (resultStr.indexOf("E") > -1) {
                NumberFormat formate = NumberFormat.getNumberInstance();
                formate.setMaximumFractionDigits(8);
                formate.setMaximumIntegerDigits(8);
                resultStr = formate.format(result);
            }
            return resultStr;
        }
    }

    /**
     * 带千分位format
     * - 少位补0，多位四舍五入
     * @param rate 保留位数
     */
    public static String doubleFormat(double value, int rate) {
        StringBuilder format;
        if (rate > 0) {
            format = new StringBuilder("#,##0.");
            for (int i = 0; i < rate; i++) {
                format.append("0");
            }
        } else {
            format = new StringBuilder("#,###");
        }
        DecimalFormat df = new DecimalFormat(format.toString());
        //设置为四舍五入
        df.setRoundingMode(RoundingMode.HALF_UP);
        //解决-0.0问题
        if (value < 1 && value > -1 && Double.parseDouble(df.format(value)) == 0) {
            value = 0D;
        }
        return df.format(value);
    }
    /**
     * 带千分位format(不加逗号 eg->4321.11)
     *
     * @param value
     * @param rate
     * @return
     */
    public static String doubleFormatNoComma(double value, int rate) {
        StringBuffer format = new StringBuffer("######0.");
        if (rate > 0) {
            for (int i = 0; i < rate; i++) {
                format.append("0");
            }
        }
        if (format.toString().endsWith(".")) {
            format = new StringBuffer("#,###,###");
        }
        DecimalFormat df = new DecimalFormat(format.toString());
        df.setRoundingMode(RoundingMode.HALF_UP);//设置为四舍五入
        return df.format(value);
    }

    /**
     * 带千分位format,保留传入数据的精度
     *
     * @param value
     * @return
     */
    public static String doubleFormat(double value) {
        String valueStr = value + "";
        int rate = 0;
        if (!StringUtil.isEmpty(valueStr) && valueStr.indexOf(".") > -1) {
            String[] tempStr = valueStr.split("\\.");
            if (tempStr.length > 1) {
                rate = tempStr[1].length();
            }
            if (valueStr.contains("E")) {
                int vrate = toInteger(valueStr.substring(valueStr.indexOf("E") + 1)) + 3;
                rate -= vrate;
            }
        }
        return doubleFormat(value, rate);
    }

    public static String doubleDecimalBtc3(double value) {
        if (value == 0) {
            return "0";
        } else {
            double result = (Math.floor(value * 1000) / 1000.0);
            if (result % 1.0 == 0) {
                return (long)result + "";
            } else {
                return result + "";
            }
        }
    }

    /**
     * 按位赋值，返回新值<br>
     *
     * @param value 旧值
     * @param bit   指定位序号，从1开始
     * @param index 指定位的值，0或1
     * @return 新值
     * @date 2016-4-18
     */
    public static long setBinaryIndex(long value, int bit, int index) {
        long dex = 1l;
        if (getBinaryIndex(value, bit) == 1) {
            if (index == 0) {
                value = value - (dex << bit - 1);
            }
        } else {
            if (index == 1) {
                value = value | (dex << bit - 1);
            }
        }
        return value;
    }

    public static int setBinaryIndex(int value, int bit, int index) {
        if (getBinaryIndex(value, bit) == 1) {
            if (index == 0) {
                value -= 1 << bit - 1;
            }
        } else if (index == 1) {
            value |= 1 << bit - 1;
        }

        return value;
    }

    /**
     * 按位取值，返回指定位的值<br>
     *
     * @param value
     * @param bit   指定位序号，从1开始
     * @return
     * @author Hu Haibin
     * @date 2016-4-18
     */
    public static int getBinaryIndex(long value, int bit) {
        long remainder = 0;
        for (int i = 0; i < bit; i++) {
            long factor = value / 2;
            remainder = value % 2;
            if (factor == 0) {
                if (i < bit - 1) {
                    remainder = 0;
                }
                break;
            }
            value = factor;
        }
        return (int)remainder;
    }



    /**
     * 按位取值，返回指定位的值<br>
     * <pre>
     * 1:登录进行谷歌安全认证
     * 2:邮箱绑定
     * 3：是否短信验证
     * 4:是否谷歌验证
     * 5:免支付密码
     * 6：免二次验证码
     * 7:付款进行邮件验证
     * </pre>
     *
     * @param value
     * @param bit   指定位序号，从1开始
     * @return
     * @date 2016-4-18
     */
    public static int getBinaryIndex(int value, int bit) {
        int remainder = 0;
        for (int i = 0; i < bit; i++) {
            int factor = value / 2;
            remainder = value % 2;
            if (factor == 0) {
                if (i < bit - 1) {
                    remainder = 0;
                }
                break;
            }
            value = factor;
        }
        return remainder;
    }

    public static boolean isNumber(String number) {
        if (!StringUtil.isEmpty(number)) {
            Pattern pattern = Pattern.compile("[0-9]*");
            return pattern.matcher(number).matches();
        }
        return false;
    }



    /**
     * 从url中获得host
     *
     * @param url
     * @return
     */
    public static String getDomainFromUrl(String url) {
        String regEx = "(https?://[^/]+)/.*";
        Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }



    public static String doubleDecimalAll(double value){
        if(value == 0){
            return "0";
        }else{
            double result = value;
            String resultStr  = result+"";
            if(!StringUtil.isEmpty(resultStr) && resultStr.indexOf(".")>-1){
                String [] resultStrs = resultStr.split("[.]");
                if(resultStrs!=null && resultStrs.length>1&&resultStrs[1].length()>6){
                    result = Math.floor(value*1000000)/1000000.0;
                    resultStr = result+"";
                }
            }
            if(result % 1.0 == 0){
                resultStr =  (long)result+"";
            }
            NumberFormat formate  = NumberFormat.getNumberInstance();
            formate.setMaximumFractionDigits(12);
            formate.setMaximumIntegerDigits(12);
            resultStr = formate.format(result);
            return resultStr;
        }

    }

    public static String toPlainString(double d) {
        return BigDecimal.valueOf(d).stripTrailingZeros().toPlainString();
    }

    /**
     * 转百分比字符串
     * @author xiangsheng.wu
     * @param scale 小数位数
     */
    public static String toPercentageString(double value, int scale) {
        BigDecimal tmp = new BigDecimal(Double.toString(value));
        BigDecimal res = tmp.multiply(new BigDecimal(100)).setScale(scale, BigDecimal.ROUND_HALF_UP);

        return res.stripTrailingZeros().toPlainString() + "%";
    }

    /**
     * 字符串拼接工具
     */
    public static String concatStr(Object... args) {
        if (args != null && args.length > 0) {
            return StringUtils.join(args);
        }
        return StringUtil.EMPTY;
    }
}