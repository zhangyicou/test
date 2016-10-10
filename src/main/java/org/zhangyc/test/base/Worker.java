package org.zhangyc.test.base;

/**
 * Created by user on 16/7/18.
 */
public class Worker extends Person {
    private Integer jobType=1;
    private String company="艺龙";
    private Double pay=200d;

    public Worker(
            Long id, String name, Integer age,
            Integer jobType, String company, Double pay) {
        super(id, name, age);
        this.jobType = jobType;
        this.company = company;
        this.pay = pay;
    }

    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Double getPay() {
        return pay;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "jobType=" + jobType +
                ", company='" + company + '\'' +
                ", pay=" + pay +
                '}';
    }
}
