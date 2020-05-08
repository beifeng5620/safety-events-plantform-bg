package com.wuhe.background.view;

import org.springframework.util.StringUtils;

/**
 * @author wuhe
 * @Date 2020/5/4 - 21:58
 */
public class SubmitEventView {
    // 经纬度
    Double lng;
    Double lat;
    // 事件类型
    String eventType;
    // 发生时间
    String time;
    // 联系方式
    String contact;
    // 详情
    String details;
    // IP
    String ip;

    public SubmitEventView(Double lng, Double lat, String eventType, String time, String contact, String details, String ip) {
        this.lng = lng;
        this.lat = lat;
        this.eventType = eventType;
        this.time = time;
        this.contact = contact;
        this.details = details;
        this.ip = ip;
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

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "SubmitEventView{" +
                "lng=" + lng +
                ", lat=" + lat +
                ", eventType=" + eventType +
                ", time='" + time + '\'' +
                ", contact='" + contact + '\'' +
                ", details='" + details + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }

    /**
     * 判断入参是否是null,只要有一个是空值，就返回true
     *
     * @return
     */
    public boolean isEmpty() {
        if (null == this.lng || null == this.lat || StringUtils.isEmpty(this.eventType) ||
                StringUtils.isEmpty(this.time) || StringUtils.isEmpty(this.contact) ||
                StringUtils.isEmpty(this.details) || StringUtils.isEmpty(this.ip)) {
            return true;
        } else {
            return false;
        }
    }
}
