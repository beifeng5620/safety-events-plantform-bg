package com.wuhe.background.entity;

/**
 * @author wuhe
 * @Date 2020/5/7 - 14:09
 * 对应数据库black_list表
 */
public class BlackList {
    String id;
    String ip;

    public BlackList() {
    }

    public BlackList(String id, String ip) {
        this.id = id;
        this.ip = ip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "BlackList{" +
                "id='" + id + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
