package org.zhangyc.test.equals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yichu.zhang
 * @Date: 2019-07-01 12:23
 */
public class EqualTest {
    public static void main(String[] args) {
        Order p1 = new Order();
        p1.setId(10000L);
        Order p2 = new Order();
        p2.setId(10000L);
        System.out.println(
                "p1.equals(p2)=" + p1.equals(p2) + ", p1.hashcode=" + p1.hashCode() + ", p2.hashcode=" +p2.hashCode());
        List<Order> set = new ArrayList<>();
        set.add(p1);
        if(!set.contains(p2)) {
            set.add(p2);
        }
        System.out.println(set);
    }
}
@Data
@NoArgsConstructor
@AllArgsConstructor
class Order {


    /**
     * 订单id
     */
    private long id;
    /**
     * 用户id
     */
    private long userId;
    /**
     * 券商id
     */
    private int brokerId;
    /**
     * 账户类型
     * AccountTypeEnum
     */
    private byte accountType;
    /**
     * 合约类型
     * InstrumentTypeEnum
     */
    private byte instrumentType;
    /**
     * 币种id
     */
    private int coinId;
    /**
     * 期权合约id
     */
    private long instrumentId;
    /**
     * 方向
     */
    private byte side;
    /**
     * 委托数量(张)
     */
    private long amount;
    /**
     * 委托价格
     */
    private double price;
    /**
     * 清算价,默认和成交价一样
     */
    private double priceAvg;
    /**
     * 成交数量
     */
    private long dealAmount;
    /**
     * 成交金额
     */
    private double dealValue;
    /**
     * 平仓收益
     */
    private double closeProfit;
    /**
     * 手续费
     */
    private double fee;
    /**
     * 手续费率
     */
    private double feeRate;
    /**
     * 0:未成交 1:部分成交 2:完全成交 3:撤单中 5:修改中
     * OrderStatusEnum
     */
    private byte status;
    /**
     * 柜台最后处理的事件id
     */
    private long lastEventId;
    /**
     * 0:web 1:安卓 2:ios 3:api
     * SourceTypeEnum
     */
    private byte source;
    /**
     * 0:限价单 1:市价单
     * OrderTypeEnum
     */
    private byte orderType;
    /**
     * 0:开仓 1:清算 2：爆仓 3:平仓 4:减仓
     * OrderSystemTypeEnum
     */
    private byte systemType;
    /**
     * 客户端订单标识
     */
    private String clientOrderId;
    /**
     * 错误码,特殊订单标识
     */
    private int messageCode;
    /**
     * 创建时间
     */
    private long createTime;
    /**
     * 修改时间
     */
    private long modifyTime;

    /**
     * 修改改事件的最大数量
     */
    private long maxAmount;
    /**
     * 修改事件占用保证金最大的价格
     */
    private double maxPrice;
    /**
     * 合约名称
     */
    private String instrumentName;
    /**
     * 指数id
     */
    private int indexId;
    /**
     * 最新成交价格
     */
    private double lastFillPx;
    /**
     * 最新成交数量
     */
    private long lastFillQty;
    /**
     * 最新成交时间
     */
    private long lastFillTime;

    @Override
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }

        if (o instanceof Order) {
            final Order order = (Order) o;
            return this.getId() == order.getId();
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(this.getId()).hashCode();
    }
}
