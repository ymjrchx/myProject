package com.dgg.Bean;

import java.io.Serializable;

public class DggPhoneInfo implements Serializable {

    private Long id; //用户id
    private String loginName;//工号
    private Integer call_30s_counts;//大于30s的
    private Integer call_60s_counts;//大于60s的
    private Integer call_all_counts;//有通话记录的
    private String dataDate;//日期

    public DggPhoneInfo() {
    }

    public DggPhoneInfo(Long id, String loginName, Integer call_30s_counts, Integer call_60s_counts, Integer call_all_counts, String dataDate) {
        this.id = id;
        this.loginName = loginName;
        this.call_30s_counts = call_30s_counts;
        this.call_60s_counts = call_60s_counts;
        this.call_all_counts = call_all_counts;
        this.dataDate = dataDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Integer getCall_30s_counts() {
        return call_30s_counts;
    }

    public void setCall_30s_counts(Integer call_30s_counts) {
        this.call_30s_counts = call_30s_counts;
    }

    public Integer getCall_60s_counts() {
        return call_60s_counts;
    }

    public void setCall_60s_counts(Integer call_60s_counts) {
        this.call_60s_counts = call_60s_counts;
    }

    public Integer getCall_all_counts() {
        return call_all_counts;
    }

    public void setCall_all_counts(Integer call_all_counts) {
        this.call_all_counts = call_all_counts;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }

    @Override
    public String toString() {
        return "DggPhoneInfo{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", call_30s_counts=" + call_30s_counts +
                ", call_60s_counts=" + call_60s_counts +
                ", call_all_counts=" + call_all_counts +
                ", dataDate='" + dataDate + '\'' +
                '}';
    }
}
