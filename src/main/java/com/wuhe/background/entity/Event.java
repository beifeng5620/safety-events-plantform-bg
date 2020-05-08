package com.wuhe.background.entity;

import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author wuhe
 * @Date 2020/5/7 - 14:11
 * 对应数据库event表
 */
public class Event {
    String id;
    Double lng;
    Double lat;
    Date time;
    String eventTypeId;
    String flag;
    String details;
    String remark;

    public Event() {
    }

    public Event(String id, Double lng, Double lat, Date time, String eventTypeId, String flag, String details, String remark) {
        this.id = id;
        this.lng = lng;
        this.lat = lat;
        this.time = time;
        this.eventTypeId = eventTypeId;
        this.flag = flag;
        this.details = details;
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(String eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", lng=" + lng +
                ", lat=" + lat +
                ", time=" + time +
                ", eventTypeId='" + eventTypeId + '\'' +
                ", flag='" + flag + '\'' +
                ", details='" + details + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    /**
     * 判断必须的字段是否为null或者空值
     * 允许ID(添加时自动生成)和remark(给管理员备注的字段)为空
     * @return
     */
    public boolean isEmpty() {
        if (null == this.lng || null == this.lat || null == this.time ||
                StringUtils.isEmpty(this.eventTypeId) || StringUtils.isEmpty(this.flag) ||
                StringUtils.isEmpty(this.details) ){
            return true;
        } else {
            return false;
        }
    }
}
