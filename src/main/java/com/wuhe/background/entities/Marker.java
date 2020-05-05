package com.wuhe.background.entities;

import java.util.Date;

/**
 * @author wuhe
 * @Date 2020/4/30 - 14:46
 */
public class Marker {

    Integer id;
    // 经纬度
    Point pt;
    // 事件类型
    Integer type;
    // 事件名称
    String typeName;
    // 发生时间
    Date time;
    // 细节详情
    String details;

    public Marker(Integer id,Double lng,Double lat, Integer type, String typeName, Date time, String details) {
        this.id = id;
        this.pt = new Point(lng,lat);
        this.type = type;
        this.typeName = typeName;
        this.time = time;
        this.details = details;
    }

    public Marker(Integer id, Point pt, Integer type, String typeName, Date time, String details) {
        this.id = id;
        this.pt = pt;
        this.type = type;
        this.typeName = typeName;
        this.time = time;
        this.details = details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Point getPt() {
        return pt;
    }

    public void setPt(Point pt) {
        this.pt = pt;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Maker{" +
                "id=" + id +
                ", pt=" + pt +
                ", type=" + type +
                ", typeName='" + typeName + '\'' +
                ", time=" + time +
                ", details='" + details + '\'' +
                '}';
    }
}
