package com.wuhe.background.entity;

/**
 * @author wuhe
 * @Date 2020/5/7 - 14:18
 * 对应数据库sys_admin表
 */
public class SysAdmin {
    String id;
    String account;
    String password;
    String permissionLevel;

    public SysAdmin() {
    }

    public SysAdmin(String id, String account, String password, String permissionLevel) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.permissionLevel = permissionLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(String permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    @Override
    public String toString() {
        return "SysAdmin{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", permissionLevel='" + permissionLevel + '\'' +
                '}';
    }
}
