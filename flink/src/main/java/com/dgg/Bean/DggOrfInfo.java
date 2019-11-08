package com.dgg.Bean;
import java.io.Serializable;

/**
 * @Classname DggOrfInfo
 * @Description TODO
 * @Date 2019/5/8 10:27
 * @Created by dgg-yanshun
 */
public class DggOrfInfo extends DggBasicInfo implements Serializable {

    private Double performanceAmount;//业绩
    private String dataDate;//日期
    private Integer isComplete;//是否完成
    private String tableName;
    private Double differenceAmount;//业绩差值

    public Double getPerformanceAmount() {
        return performanceAmount;
    }

    public void setPerformanceAmount(Double performanceAmount) {
        this.performanceAmount = performanceAmount;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }

    public Integer getIsComplete() { return isComplete; }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Double getDifferenceAmount() {
        return differenceAmount;
    }

    public void setDifferenceAmount(Double differenceAmount) {
        this.differenceAmount = differenceAmount;
    }

    @Override
    public String toString() {
        return "DggOrfInfo{" +
                "performanceAmount=" + performanceAmount +
                ", dataDate='" + dataDate + '\'' +
                ", isComplete=" + isComplete +
                ", tableName='" + tableName + '\'' +
                ", differenceAmount=" + differenceAmount +
                ", id=" + id +
                ", userId=" + userId +
                ", loginName='" + loginName + '\'' +
                ", status='" + status + '\'' +
                ", eventType='" + eventType + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}
