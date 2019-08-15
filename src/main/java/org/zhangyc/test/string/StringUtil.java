package org.zhangyc.test.string;

public class StringUtil {
    /**
     * 按位赋值，返回新值<br>
     *
     * @param value 旧值
     * @param bit   指定位序号，从1开始
     * @param index 指定位的值，0或1
     * @return 新值
     * @date 2016-4-18
     */
    public static long setBinaryIndex(long value, final int bit, final int index) {
        final long dex = 1L;
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

    public static int setBinaryIndex(int value, final int bit, final int index) {
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
    public static int getBinaryIndex(long value, final int bit) {
        long remainder = 0;
        for (int i = 0; i < bit; i++) {
            final long factor = value / 2;
            remainder = value % 2;
            if (factor == 0) {
                if (i < bit - 1) {
                    remainder = 0;
                }
                break;
            }
            value = factor;
        }
        return (int) remainder;
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
    public static int getBinaryIndex(int value, final int bit) {
        int remainder = 0;
        for (int i = 0; i < bit; i++) {
            final int factor = value / 2;
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

    public static String optimizeEmpty(final String key) {
        if (key == null || "null".equals(key)) {
            return "";
        }
        return key;
    }

    public static Integer toInteger(final String s, final Integer defaultValue) {
        if (s == null || "".equals(s.trim()) || !s.matches("^[-+]?[0-9]+$")) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(s.trim());
        } catch (final Exception e) {
            //log.error("", e);
            return defaultValue;
        }
    }

    public static String[] safeSplit(final String text, final String separator) {
        return text.split("\\" + separator);
    }

    public static boolean isNullOrEmpty(final String input) {
        return input == null || input.isEmpty();
    }

    public static String buildString(final Object first, final Object... inputs) {
        final StringBuilder sb = new StringBuilder();
        sb.append(first);
        for (final Object input : inputs) {
            sb.append(input);
        }
        return sb.toString();
    }
}
