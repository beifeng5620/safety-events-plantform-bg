package com.wuhe.background.entities;

import java.util.Arrays;

/**
 * @author wuhe
 * @Date 2020/5/5 - 0:38
 */
public class EventWeekly extends Event {

    // [周日,周一, 周二, 周三, 周四, 周五, 周六] 数目
    Long[] weeklyHappened;

    public EventWeekly() {}

    public EventWeekly(String id, String name, Long[] weeklyHappened) {
        super(id, name);
        this.weeklyHappened = weeklyHappened;
    }

    public Long[] getWeeklyHappened() {
        return weeklyHappened;
    }

    public void setWeeklyHappened(Long[] weeklyHappened) {
        this.weeklyHappened = weeklyHappened;
    }

    @Override
    public String toString() {
        return "EventWeekly{" +
                "weeklyHappened=" + Arrays.toString(weeklyHappened) +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
