package com.dgg.Bean;

/**
 * @Classname DggUserInfo
 * @Description TODO
 * @Date 2019/5/7 10:42
 * @Created by dgg-yanshun
 */
public class DggUserInfo {

    private Long id;//用户id
    private String loginName;//用户工号
    private String seatNumber;//外呼号
    private String voipUsername;//电信VOIP帐号
    private String orgId;//部门id

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

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getVoipUsername() {
        return voipUsername;
    }

    public void setVoipUsername(String voipUsername) {
        this.voipUsername = voipUsername;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        return "DggUserInfo{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", voipUsername='" + voipUsername + '\'' +
                ", orgId='" + orgId + '\'' +
                '}';
    }
}
