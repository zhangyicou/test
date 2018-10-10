package org.zhangyc.test.excel.jdgift;

/**
 * Created by zhang on 2018/9/19.
 */
public class JDGiftInfo {
    private String userId;//用户
    private String taskSign;//活动标识
    private String giftName;//商品名称
    private String cardNo;//卡号
    private String expressNo;//密码
    private String createTime;//发放时间
    private String giftValidTime;//失效时间

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskSign() {
        return taskSign;
    }

    public void setTaskSign(String taskSign) {
        this.taskSign = taskSign;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getGiftValidTime() {
        return giftValidTime;
    }

    public void setGiftValidTime(String giftValidTime) {
        this.giftValidTime = giftValidTime;
    }
}
