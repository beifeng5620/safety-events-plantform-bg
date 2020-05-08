package com.wuhe.background.entity;

/**
 * @author wuhe
 * @Date 2020/5/7 - 14:16
 * 对应数据库event_type表
 */
public class EventType {
    String id;
    String eventName;

    public EventType() {
    }

    public EventType(String id, String eventName) {
        this.id = id;
        this.eventName = eventName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    @Override
    public String toString() {
        return "EventType{" +
                "id='" + id + '\'' +
                ", eventName='" + eventName + '\'' +
                '}';
    }
}
