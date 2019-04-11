package org.zhangyc.test.set;

import java.math.BigDecimal;

public class DLine implements Comparable<DLine> {

    private byte side;
    private BigDecimal price;
    private BigDecimal totalSize;
    private int totalCount;
    private int liquidCount;
    public DLine() {}

    @Override
    public int compareTo(DLine o) {
        return o.getPrice().compareTo(this.getPrice());
    }

    public byte getSide() {
        return side;
    }

    public void setSide(byte side) {
        this.side = side;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(BigDecimal totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getLiquidCount() {
        return liquidCount;
    }

    public void setLiquidCount(int liquidCount) {
        this.liquidCount = liquidCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof DLine)) {
            return false;
        }
        DLine anDepthLine = (DLine) obj;
        if (this.side != anDepthLine.getSide()) {
            return false;
        }
        if (!this.price.equals(anDepthLine.getPrice())) {
            return false;
        }
        if (!this.totalSize.equals(anDepthLine.getTotalSize())) {
            return false;
        }
        if (this.totalCount != anDepthLine.getTotalCount()) {
            return false;
        }
        return this.liquidCount == anDepthLine.getLiquidCount();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
