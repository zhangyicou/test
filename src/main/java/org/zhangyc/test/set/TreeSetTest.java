package org.zhangyc.test.set;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetTest {
    public static void main(String[] args) {
        TreeSet<DLine> set = new TreeSet<DLine>();

        DLine line = new DLine();
        line.setPrice(BigDecimal.ONE);
        line.setSide((byte)1);
        line.setTotalCount(1);
        line.setTotalSize(BigDecimal.ONE);
        set.add(line);

        line = new DLine();
        line.setPrice(BigDecimal.TEN);
        line.setSide((byte)1);
        line.setTotalCount(1);
        line.setTotalSize(BigDecimal.TEN);
        set.add(line);

        line = new DLine();
        line.setPrice(BigDecimal.ONE);
        line.setSide((byte)2);
        line.setTotalCount(2);
        line.setTotalSize(BigDecimal.TEN);
        set.add(line);

        Iterator<DLine> iterable = set.iterator();
        while (iterable.hasNext()){
            DLine dLine = iterable.next();
            System.out.println(dLine.getPrice().intValue() + "; " + dLine.getTotalCount());
        }

    }
}
