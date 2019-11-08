package com.dgg.Bean;

import java.sql.Timestamp;

/**
 * @Classname DggBasicInfo
 * @Description 公共属性表
 * @Date 2019/4/29 9:31
 * @Created by dgg-yanshun
 */
public class DggBasicInfo {
    public Long id;//数据id，具有唯一性，不是商务id
    public Long userId;//员工id
    public String loginName;//员工工号
    public String status;//状态
    public String eventType;//数据状态，更新、删除、新增
    public Timestamp updateTime;//更新时间
    public Timestamp createTime;//创建时间

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

}
