package org.zhangyc.test.thread;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class PrecisionUtil {
    public static final int INITIAL_CAPACITY = 16;
    private static final PrecisionUtil instance = new PrecisionUtil();
    private final ThreadLocal<Map<Integer, NumberFormat>> threadLocal = ThreadLocal.withInitial(this::initialValue);

    private PrecisionUtil() {
    }

    public static PrecisionUtil getInstance() {
        return instance;
    }

    protected Map<Integer, NumberFormat> initialValue() {
        final Map<Integer, NumberFormat> map = new HashMap<>(INITIAL_CAPACITY);

        for (int i = 0; i <= INITIAL_CAPACITY; i++) {
            map.put(i, this.createFormater(i));
        }
        return map;
    }

    private NumberFormat createFormater(final int digits) {
        final NumberFormat format = DecimalFormat.getInstance();
        format.setMaximumFractionDigits(digits);
        format.setMinimumFractionDigits(0);
        format.setGroupingUsed(false);
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format;
    }

    public String formatNumber(final double value, final int digits, boolean isUp) {
        if (Double.isNaN(value)) {
            return String.valueOf(value);
        }
        //进位
        if(isUp){
            double a = Math.pow(10, digits);
            System.out.println(Math.ceil(value * a));
            return this.threadLocal.get().get(digits).format(Math.ceil(value * a)/a);
        }
        //截位
        else {
            double a = Math.pow(10, digits);
            return this.threadLocal.get().get(digits).format(Math.round(value * a) / a);
        }
    }

    public String formatNumber(final double value, final int digits) {
        if (Double.isNaN(value)) {
            return "";
        }

        return this.threadLocal.get().get(digits).format(value);
    }
}
