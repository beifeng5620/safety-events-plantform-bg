package com.wuhe.background.entity;

import java.util.Date;

/**
 * @author wuhe
 * @Date 2020/5/7 - 14:20
 * 对应数据库sys_admin_log表
 */
public class SysAdminLog {
    String id;
    String sysAdminId;
    String operation;
    Date time;

    public SysAdminLog() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSysAdminId() {
        return sysAdminId;
    }

    public void setSysAdminId(String sysAdminId) {
        this.sysAdminId = sysAdminId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "SysAdminLog{" +
                "id='" + id + '\'' +
                ", sysAdminId='" + sysAdminId + '\'' +
                ", operation='" + operation + '\'' +
                ", time=" + time +
                '}';
    }
}
