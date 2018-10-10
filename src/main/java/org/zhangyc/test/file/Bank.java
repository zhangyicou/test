package org.zhangyc.test.file;

/**
 * Created by zhang on 2018/9/25.
 */
public class Bank {
    private String bankCode;
    private String bankCodeError;
    private String bankName;
    private String bankNameError;
    private String cardNo;
    private long userBcardId;
    private long userId;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankCodeError() {
        return bankCodeError;
    }

    public void setBankCodeError(String bankCodeError) {
        this.bankCodeError = bankCodeError;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNameError() {
        return bankNameError;
    }

    public void setBankNameError(String bankNameError) {
        this.bankNameError = bankNameError;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public long getUserBcardId() {
        return userBcardId;
    }

    public void setUserBcardId(long userBcardId) {
        this.userBcardId = userBcardId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
