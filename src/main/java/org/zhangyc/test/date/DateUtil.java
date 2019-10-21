package org.zhangyc.test.date;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.shade.org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

@Slf4j
public class DateUtil {

    /**
     * 年月日   yyyy-MM-dd
     */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_FORMAT_MILLIS = "yyyy-MM-dd HH:mm:ss.S";

    public static final String DATE_TIME_YYYY_MM = "yyyyMM";

    public static final String SETTLEMENT_TIME = "yyyy-MM-dd 16:00:00";

    public static final String FORECASTPRICE_TIME = "yyyy-MM-dd HH:00:00";

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final DateFormat dfDateTime = new SimpleDateFormat(DATE_TIME_FORMAT);
    /**
     * yyyy-MM-dd HH:mm
     */
    public static final DateFormat dfDateTime2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    /**
     * yyyyMMdd_HHmmss
     */
    public static final DateFormat dfDateTime4 = new SimpleDateFormat("yyyyMMdd_HHmmss");
    public static String TIME_STYLE_S1 = "yyyy-MM-dd";
    public static String TIME_STYLE_S2 = "yyyy-MM-dd HH:mm";
    public static String TIME_STYLE_S3 = "yyyy-MM-dd HH:mm:ss";
    public static String TIME_STYLE_S4 = "yyyy-MM-dd HH:mm:ss.S";
    public static String TIME_STYLE_S5 = "yyyy-MM-dd HH:mm:ss.S E zZ";
    public static String TIME_STYLE_S6 = "yyyyMMddHHmmssS";
    public static String TIME_STYLE_S7 = "yyyy年MM月dd日HH时mm分ss秒";
    public static String TIME_STYLE_S10 = "yyyyMMdd";
    public static String TIME_STYLE_S11 = "yyyy-MM-dd.HH:mm:ss.S";
    public static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
    public static SimpleDateFormat SDF2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");

    static {
        SDF.setTimeZone(TimeZone.getTimeZone("UTC"));
        SDF2.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static String getFormatDate(Date date) {
        if (date == null) {
            return "";
        }
        return dfDateTime2.format(date);
    }

    public static String getFormatDate(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static Date getNewDateByAdd(Date d, int days) {
        int Year, Month, Day;
        boolean rYear;
        int[] DayOfMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        Calendar cal;
        cal = Calendar.getInstance();
        cal.setTime(d);

        Year = cal.get(Calendar.YEAR);
        Month = cal.get(Calendar.MONTH);
        Day = cal.get(Calendar.DAY_OF_MONTH);

        Day = Day + days;

        rYear = (Year % 4 == 0 && Year % 100 != 0) || Year % 400 == 0;

        if (rYear) {
            DayOfMonths[1] = 29;
        } else {
            DayOfMonths[1] = 28;
        }

        while (Day > DayOfMonths[Month]) {
            Day = Day - DayOfMonths[Month];
            Month++;
            if (Month > 11) {
                Month = 0;
                Year++;
            }
        }

        cal.set(Year, Month, Day);
        return cal.getTime();
    }

    /**
     * 获 date 时间  elapsedHourValue  小时前的时间
     *
     * @param elapsedHourValue
     * @return
     */
    public static Date getSubtractedDateByElapsedHourValue(Date date, long elapsedHourValue) {
        long elapsedTimeInMillis = elapsedHourValue * 60 * 60 * 1000;
        long currentTimeInMillis = date.getTime();

        long previousTimeInMillis = currentTimeInMillis - elapsedTimeInMillis;
        Date previousDate = new Date(previousTimeInMillis);
        return previousDate;
    }

    /**
     * 获 date 时间  elapsedHourValue  小时前的时间
     *
     * @param elapsedHourValue
     * @return
     */
    public static Date getSubtractedDateByElapsedHourValue(Date date, int elapsedHourValue) {
        return getSubtractedDateByElapsedHourValue(date, (long) elapsedHourValue);
    }

    public static int getDifferentDaysBetweenTwoDates(Date beforeDate, Date afterDate) {
        long diffMillis = afterDate.getTime() - beforeDate.getTime();

        long diffDays = diffMillis / (24 * 60 * 60 * 1000);

        return (int) diffDays;
    }

    public static int getDifferentSecondsBetweenTwoDates(Date beforeDate, Date afterDate) {
        long diffMillis = afterDate.getTime() - beforeDate.getTime();

        long diffSeconds = diffMillis / 1000;

        return (int) diffSeconds;
    }

    /**
     * 获取当月总天数
     *
     * @param date
     * @return
     */
    public static int getActualMaximumDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DATE);
    }

    /**
     * String ==> java.util.Date
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date dateString2Util(String dateStr, String format) {
        return new SimpleDateFormat(format).parse(dateStr, new ParsePosition(0));
    }

    /**
     * String ==> java.util.Date
     *
     * @param dateStr
     * @return
     */
    public static Date dateString2UtilyyyyMMdd(String dateStr) {
        return new SimpleDateFormat(YYYY_MM_DD).parse(dateStr, new ParsePosition(0));
    }

    /**
     * java.util.Date ==> String
     *
     * @return
     */
    public static String dateUtil2String(Date date, String format) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(format).format(date);
    }

    public static String dateUtil2StringDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return dfDateTime.format(date);
    }

    public static int getMonth() {
        return new GregorianCalendar().get(Calendar.MONTH) + 1;
    }

    public static int getDay() {
        return new GregorianCalendar().get(Calendar.DATE);
    }

    public static Date getDateByString(String dateStr, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static Date getBeginningOfDayDate() {
        Calendar c = Calendar.getInstance();
        c.set(11, 0);
        c.set(12, 0);
        c.set(13, 0);
        return new Date(c.getTimeInMillis());
    }

    public static Date getDate() {
        Calendar beginningOfDayCalendar = Calendar.getInstance();
        beginningOfDayCalendar.set(Calendar.HOUR_OF_DAY, 0);
        beginningOfDayCalendar.set(Calendar.MINUTE, 0);
        beginningOfDayCalendar.set(Calendar.SECOND, 0);
        beginningOfDayCalendar.set(Calendar.MILLISECOND, 0);
        return beginningOfDayCalendar.getTime();
    }

    /**
     * yyyyMMdd_HHmmss
     *
     * @return
     * @author 胡海斌(Haibin.hu @ okcoin.com)
     */
    public static String getDateStr() {
        return dfDateTime4.format(new GregorianCalendar().getTime());
    }

    /**
     * 获得据今天dateNum间隔的时间
     *
     * @param date    基准日期
     * @param dateNum 时间间隔。
     * @param type    dateNum代表的类型。如Calendar.SECOND代表秒, Calendar.DATE代表天
     * @return 异常情况返回NULL
     */
    public static Date getDate(Date date, int dateNum, int type) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(type, dateNum);
            return cal.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取今天日期以前，或以后的时间
     * 如果是正数则是以后的时间，如果是负数则是以前的时间
     */
    public static Date getDateInHourAgo(Date date, int hourNum) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hourNum);
        return cal.getTime();
    }

    /**
     * 获取当前日期是星期几
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static int getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w <= 0) {
            w = 7;
        }

        return w;
    }

    /**
     * 获取当前日期是星期几
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static int getWeekOfDate(int dt) {
        int w = dt + 1;
        if (w > 7) {
            w = 1;
        }
        return w;
    }

    /**
     * date 日期加上，或减去几天
     *
     * @param dateNum
     * @return
     */
    public static Date getDateInDayAgo(int dateNum) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, dateNum);
        return cal.getTime();
    }

    /**
     * date 日期加上，或减去几天
     *
     * @param date
     * @param dateNum
     * @return
     */
    public static Date getDateInDayAgo(Date date, int dateNum) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, dateNum);
        return cal.getTime();
    }

    /**
     * 获取 date 日期加上  或  减去几月
     *
     * @param date
     * @param monthNum
     * @return
     */
    public static Date getDateInMonthAgo(Date date, int monthNum) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, monthNum);
        return cal.getTime();
    }

    public static Date getDateInMonthAgo(int monthNum) {
        return getDateInMonthAgo(new Date(), monthNum);
    }

    /**
     * date 分钟
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date getDateInMinuteAgo(Date date, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minute);
        return cal.getTime();
    }

    public static Date plusMinutes(Date date, int minute) {
        return new Date(date.getTime() + minute * 1000 * 60);
    }

    /**
     * 获取 时间  增加多少秒 还是减少多少秒
     *
     * @param date
     * @param second
     * @return
     */
    public static Date getDateInSecondAgo(Date date, int second) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, second);
        return cal.getTime();
    }

    /**
     * 获得下一个星期五
     *
     * @param date
     * @return
     */
    public static Date getNexFriday(Date date) {
        date = getDateInDayAgo(date, 14);
        int week = getWeekOfDate(date);
        if (week == 5) {
            return date;
        }
        Date d;
        int i = week - 5;
        if (i > 0) {
            d = getDateInDayAgo(date, -i);
        } else {
            i = 7 - (5 - week);
            d = getDateInDayAgo(date, -i);
        }
        return d;
    }

    /**
     * 比较两个时刻
     *
     * @param timeA 格式"2012-11-11 11:11:00"
     * @return timeA>timeB是 true 否 false
     */
    public static boolean compare(String timeA, String timeB) {
        Date dateA = DateUtil.dateString2Util(timeA, DATE_TIME_FORMAT);
        Date dateB = DateUtil.dateString2Util(timeB, DATE_TIME_FORMAT);
        if (dateA == null || dateB == null) {
            return false;
        }

        return dateA.after(dateB);
    }

    /**
     * timeA是否在timeB之后，相同返回false
     *
     * @param timeA eg: 2019-07-15 14:04:26.456
     * @param timeB eg: 2019-07-15 14:04:26.123
     * @return eg: true
     */
    public static boolean compareAfterMillis(String timeA, String timeB) {
        Date dateA = DateUtil.dateString2Util(timeA, DATE_TIME_FORMAT_MILLIS);
        Date dateB = DateUtil.dateString2Util(timeB, DATE_TIME_FORMAT_MILLIS);
        if (dateA == null || dateB == null) {
            return false;
        }
        return dateA.after(dateB);
    }

    /**
     * 获得当前时间elapsedSecondValue前后的时间
     *
     * @param elapsedSecondValue 分钟
     * @return
     */
    public static Date getSubtractedDateByElapsedSecondValue(
            long currentTimeInMillis, double elapsedSecondValue) {
        long elapsedTimeInMillis = (long) (elapsedSecondValue * 60.0 * 1000.0);

        long previousTimeInMillis = currentTimeInMillis - elapsedTimeInMillis;
        Date previousDate = new Date(previousTimeInMillis);
        return previousDate;
    }

    /**
     * 当前时间减指定天数
     *
     * @param numberOfDate
     * @return
     */
    public static Date getSubtractedDate(int numberOfDate) {
        Calendar calendar = Calendar.getInstance();
        int substractedDays = numberOfDate *= -1;
        calendar.add(5, substractedDays);
        return calendar.getTime();
    }

    public static Date[] getDateInDayZero() {
        Date[] arrDate = new Date[6];
        Date date = new Date();
        date.setMinutes(0);
        date.setSeconds(0);
        if (date.getHours() < 8) {
            date.setHours(0);
        } else if (date.getHours() < 16) {
            date.setHours(8);
        } else {
            date.setHours(16);
        }

        for (int i = 0; i < 6; ++i) {
            arrDate[i] = getDateInHourAgo(date, -8 * i);
        }

        return arrDate;
    }

    /**
     * Returns time string convert to Date. <br/>
     *
     * @param timeString string time, if time=null, returns the current Date.
     * @param style      Format number
     * @return converted {@link Date}
     * @author Tony Tian
     * @version 1.0.0
     * @updated log: Updated On 2018-09-01 By Tony
     * Styles:
     * <p>
     * <blockquote><pre>
     *         1:  2018-09-01                           -> Sat Sep 01 00:00:00 CST 2018
     *         2:  2018-09-01 09:51                     -> Sat Sep 01 09:51:00 CST 2018
     *         3:  2018-09-01 09:51:25                  -> Sat Sep 01 09:51:25 CST 2018
     *         4:  2018-09-01 09:51:25.182              -> Sat Sep 01 09:51:25 CST 2018
     *         5:  2018-09-01 09:51:25.182 Sat CST+0800 -> Sat Sep 01 09:51:25 CST 2018
     *         6:  20180901095125185                    -> Sat Sep 01 09:51:25 CST 2018
     *         7:  2018年09月01日09时51分25秒             -> Sat Sep 01 09:51:25 CST 2018
     *         10:  20180901                            -> Sat Sep 01 00:00:00 CST 2018
     *         11:  2018-09-01.09:51:25.192             -> Sat Sep 01 09:51:25 CST 2018
     * </pre></blockquote>
     * </p>
     */
    public static Date stringToTime(String timeString, int style) throws ParseException {
        timeString = StringUtils.isEmpty(timeString) ? timeToString(null, 3) : timeString.trim();
        String timeStyle;
        switch (style) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 10:
            case 11: {
                timeStyle = getTimeStyle(style);
                break;
            }
            default: {
                timeStyle = TIME_STYLE_S3;
                break;
            }
        }
        return new SimpleDateFormat(timeStyle).parse(timeString);
    }

    /**
     * Returns the time string in format. <br/>
     *
     * @param time  Date object, if time=null, returns the current time.
     * @param style Format number
     * @return String time string in format
     * @author Tony Tian
     * @version 1.0.0
     * @updated log: Updated On 2018-09-01 By Tony
     * Styles:
     * <p>
     * <blockquote><pre>
     *        -1:  Sat Sep 01 09:38:30 CST 2018
     *         0:  1535765910165
     *         1:  2018-09-01
     *         2:  2018-09-01 09:38
     *         3:  2018-09-01 09:38:30
     *         4:  2018-09-01 09:38:30.166
     *         5:  2018-09-01 09:38:30.167 Sat CST+0800
     *         6:  20180901093830167
     *         7:  2018年09月01日09时38分30秒
     *         8:  2018-09-01T01:38:30.167Z
     *         9:  1535737110.168
     *         10: 20180901
     *         11: 2018-09-01.09:38:30.171
     * </pre></blockquote>
     * </p>
     */
    public static String timeToString(Date time, int style) {
        if (time == null) {
            time = new Date();
        }
        String timeStyle;
        switch (style) {
            case 0: {
                return String.valueOf(System.currentTimeMillis());
            }
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 10:
            case 11: {
                timeStyle = getTimeStyle(style);
                break;
            }
            case 8: {
                SimpleDateFormat sdf = (SimpleDateFormat) SDF.clone();
                return sdf.format(time);
            }
            case 9: {
                return getEpochTime(time);
            }
            case 12: {
                SimpleDateFormat sdf = (SimpleDateFormat) SDF2.clone();
                return sdf.format(time);
            }
            default: {
                return time.toString();
            }
        }
        return new SimpleDateFormat(timeStyle).format(time);
    }

    /**
     * {@link DateUtil#timeToString(Date, int)}
     *
     * @param time Date object, if time=null, returns null.
     */
    public static String timeToStringNull(Date time, int style) {
        return time == null ? null : timeToString(time, style);
    }

    private static String getTimeStyle(int style) {
        switch (style) {
            case 1: {
                return TIME_STYLE_S1;
            }
            case 2: {
                return TIME_STYLE_S2;
            }
            case 3: {
                return TIME_STYLE_S3;
            }
            case 4: {
                return TIME_STYLE_S4;
            }
            case 5: {
                return TIME_STYLE_S5;
            }
            case 6: {
                return TIME_STYLE_S6;
            }
            case 7: {
                return TIME_STYLE_S7;
            }
            case 10: {
                return TIME_STYLE_S10;
            }
            case 11: {
                return TIME_STYLE_S11;
            }
            default: {
                return TIME_STYLE_S3;
            }
        }
    }

    /**
     * UNIX timestamp ISO 8601 rule eg: 2018-02-03T05:34:14.110Z
     */
    public static String getUnixTime() {
        return Instant.now().toString();
    }

    /**
     * epoch time   eg: 1517662142.557
     */
    public static String getEpochTime(Date... time) {
        long milliseconds = System.currentTimeMillis();
        if (time != null && time.length > 0) {
            milliseconds = time[0].getTime();
        }
        // UTC+8 -> UTC+0  : 8 * 60 * 60 = 28800000
//        milliseconds = milliseconds - 28800000L;
        BigDecimal bd1 = new BigDecimal(milliseconds);
        BigDecimal bd2 = new BigDecimal(1000);
        return bd1.divide(bd2).toString();
    }

    /**
     * 获取下一季度合约closeDate
     *
     * @param curQuarterCloseDate 当前季度合约closeDate
     * @return
     */
    public static Date getNextQuarterDate(Date curQuarterCloseDate) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(curQuarterCloseDate);
        instance.set(Calendar.DAY_OF_MONTH, 1);
        instance.add(Calendar.MONTH, 4);
        //三个月后 当月的最后一天
        instance.add(Calendar.DAY_OF_MONTH, -1);
        if (instance.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            return instance.getTime();
        }
        instance.add(Calendar.DAY_OF_MONTH, -(instance.get(Calendar.DAY_OF_WEEK) % 7 + 1));
        return instance.getTime();
    }

    /**
     * 获取上个月的第一天 或 最后一天
     *
     * @param isStartDay true:第一天
     * @return
     */
    public static Date getPreMonthFirstOrEndDay(boolean isStartDay) {
        Calendar calendar = Calendar.getInstance();
        if (isStartDay) {
            calendar.add(Calendar.MONTH, -1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
        } else {
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.DATE, -1);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
        }
        return calendar.getTime();
    }

    /**
     * 获取n月之前时间
     *
     * @param n
     * @return
     */
    public static long getMonthsBefore(int n) {
        return new DateTime().minusMonths(n).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).getMillis();
    }

    /**
     * 获取n月之前时间格式字符串
     *
     * @param n
     * @return
     */
    public static String getMonthsBeforeStr(int n, String format) {
        return dateUtil2String(new DateTime().minusMonths(n).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).toDate(), format);
    }

    /**
     * 获取n天之前时间
     *
     * @param n
     * @return
     */
    public static long getDaysBefore(int n) {
        return new DateTime().minusDays(n).withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).getMillis();
    }

    /**
     * 获取n天之前时间 yyyy-MM-dd HH:mm:ss
     *
     * @param n
     * @return
     */
    public static String getDaysBeforeString(int n) {
        return dfDateTime.format(new DateTime().minusDays(n).withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).toDate());
    }

    /**
     * 获取n小时之前的时间
     *
     * @param hours
     * @return
     */
    public static long getMinusHoursTime(int hours) {
        return new DateTime().minusHours(hours).getMillis();
    }

    /**
     * 获取n分钟之前时间格式字符串
     *
     * @param n
     * @return
     */
    public static String getMinutesBeforeStr(int n, String format) {
        return dateUtil2String(new DateTime().minusMinutes(n).toDate(), format);
    }

    /**
     * 获取当前系统时间戳
     *
     * @return eg: 1529934310746
     */
    public static long now() {
        return System.currentTimeMillis();
    }


    /**
     * String -> UNIX timestamp ISO 8601 rule
     * eg:Tue Sep 25 10:55:25 CST 2018-> 2018-09-25T02:55:25.589Z
     */
    public static String getTZ(String t) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df1.setTimeZone(TimeZone.getTimeZone("UTC"));
        return df1.format(sdf.parse(t));
    }
    /**
     * UTC -> Date
     * eg:2017-11-09T23:16:03.562Z-> 2017-11-10 07:16:03:562
     */
    public static String getDate(String UTC) throws Exception{
        SimpleDateFormat sdf = null;
        if(UTC.contains(".")){
            sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        }else{
            sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        }
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date UtcDate = null;
        UtcDate = sdf.parse(UTC);

        SimpleDateFormat localFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        localFormater.setTimeZone(TimeZone.getDefault());
        String localTime = localFormater.format(UtcDate.getTime());
        return localTime;
    }

    /**
     * UTC -> Date
     * eg:2017-11-09T23:16:03.562Z-> 1529934310746
     */
    public static Long getTime(String UTC) throws ParseException {
        SimpleDateFormat sdf = null;
        if(UTC.contains(".")){
            sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        }else{
            sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        }
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));


        return  sdf.parse(UTC).getTime();
    }

    /**
     * String -> timestamp
     * eg:2017-11-10 07:16:03:562-> 1510269363562
     */
    public static long getTimestamp(String date) throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        long timestamp = 0;
        Date dateStart = format.parse(date);
        timestamp = dateStart.getTime();
        return timestamp;
    }

    /**
     * String -> timestamp
     * eg:2017-11-10 07:16:03-> 1510269363000
     */
    public static long getTimestamp(String date, String datePattern) {
        SimpleDateFormat format = new SimpleDateFormat(datePattern);
        try {
            return format.parse(date).getTime();
        } catch (ParseException e) {
           log.error(e.getMessage(),e);
        }
        return -1;
    }

    /**
     * 日期字符串转日期Date
     * @param dateString 2018-11-29 23:02:47
     * @param dateFormat yyyy-MM-dd HH:mm:ss
     * @return Date -> Thu Nov 29 23:02:47 CST 2018
     */
    public static Date stringToDate(String dateString, String dateFormat) {
        return new SimpleDateFormat(dateFormat).parse(dateString, new ParsePosition(0));
    }

    /**
     * 日期Date转日期字符串
     * @param date Date -> Thu Nov 29 23:02:47 CST 2018
     * @param dateFormat yyyy-MM-dd HH:mm:ss
     * @return String -> 2018-11-29 23:02:47
     */
    public static String dateToString(Date date, String dateFormat) {
        String dateString = null;
        if(date != null){
            dateString = new SimpleDateFormat(dateFormat).format(date);
        }
        return dateString;
    }

    /**
     * timestamp -> UTC  需要交验是否是10位
     * eg:1510269363562-> 2017-11-09T23:16:03.562Z
     */
    public static String getUTCByTimeStamp(String timestamp){
        if (!StringUtil.isEmpty(timestamp)) {
            if (timestamp.length() == 10) {
                timestamp = timestamp + "000";
            }
            String res;
            long lt = new Long(timestamp);
            Date date = new Date(lt);
            res = timeToString(date, 12);
            return res;
        }
        return "";
    }

    /**
     * timestamp -> UTC  无需交验是否是10位
     * eg:1510269363562-> 2017-11-09T23:16:03.562Z
     */
    public static String getUTCByTimeStamp(Long timestamp) {
        String res;
        Date date = new Date(timestamp);
        res = timeToString(date, 12);
        return res;
    }

}
