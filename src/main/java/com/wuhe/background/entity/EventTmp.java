package com.wuhe.background.entity;

import java.util.Date;

/**
 * @author wuhe
 * @Date 2020/5/7 - 14:13
 * 对应数据库event_tmp表
 */
public class EventTmp {
    String id;
    Double lng;
    Double lat;
    Date time;
    String eventTypeId;
    String contact;
    String ip;
    String flag;
    String details;

    public EventTmp() {
    }

    public EventTmp(String id, Double lng, Double lat, Date time, String eventTypeId, String contact, String ip, String flag, String details) {
        this.id = id;
        this.lng = lng;
        this.lat = lat;
        this.time = time;
        this.eventTypeId = eventTypeId;
        this.contact = contact;
        this.ip = ip;
        this.flag = flag;
        this.details = details;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    @Override
    public String toString() {
        return "EventTmp{" +
                "id='" + id + '\'' +
                ", lng=" + lng +
                ", lat=" + lat +
                ", time=" + time +
                ", eventTypeId='" + eventTypeId + '\'' +
                ", contact='" + contact + '\'' +
                ", ip='" + ip + '\'' +
                ", flag='" + flag + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
