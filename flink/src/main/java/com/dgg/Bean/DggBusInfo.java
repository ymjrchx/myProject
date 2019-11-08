package com.dgg.Bean;

import java.sql.Timestamp;

/**
 * @Classname DggBusInfo
 * @Description TODO
 * @Date 2019/5/10 11:48
 * @Created by dgg-yanshun
 */
public class DggBusInfo  {

    public Long id;//数据id，具有唯一性，不是商务id
    public Long userId;//员工id
    public String loginName;//员工工号
    public Long orgId;//部门id
    public String status;//状态
    public Timestamp createTime;//创建时间
    public Timestamp updateTime;//更新时间
    private String no;//商机
    private String tableName;//表名
    private String customerNo;//客户编号
    private String customerName;//客户名称
    private String customerPhone;//客户电话
    private Timestamp willDropTime;//即将调库的时间
    private Timestamp nextFollowTime;//下次跟进时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Timestamp getWillDropTime() {
        return willDropTime;
    }

    public void setWillDropTime(Timestamp willDropTime) {
        this.willDropTime = willDropTime;
    }

    public Timestamp getNextFollowTime() {
        return nextFollowTime;
    }

    public void setNextFollowTime(Timestamp nextFollowTime) {
        this.nextFollowTime = nextFollowTime;
    }

    @Override
    public String toString() {
        return "DggBusInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", loginName='" + loginName + '\'' +
                ", orgId=" + orgId +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", no='" + no + '\'' +
                ", tableName='" + tableName + '\'' +
                ", customerNo='" + customerNo + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", willDropTime=" + willDropTime +
                ", nextFollowTime=" + nextFollowTime +
                '}';
    }
}
