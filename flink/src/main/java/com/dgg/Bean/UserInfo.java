package com.dgg.Bean;

public class UserInfo {
    private String loginName;//工号
    private String realName;//用户名
    private String id;
    private String seatNumber;
    private String orgId;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "loginName='" + loginName + '\'' +
                ", realName='" + realName + '\'' +
                ", id='" + id + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", orgId='" + orgId + '\'' +
                '}';
    }
}
