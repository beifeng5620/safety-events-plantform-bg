package com.wuhe.background.entities;

/**
 * @author wuhe
 * @Date 2020/4/30 - 23:20
 */
public class Event {
    String id;
    String name;

    public Event() {}

    public Event(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
