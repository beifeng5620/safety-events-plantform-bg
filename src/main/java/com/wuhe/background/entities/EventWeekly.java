package com.wuhe.background.entities;

import java.util.Arrays;

/**
 * @author wuhe
 * @Date 2020/5/5 - 0:38
 */
public class EventWeekly extends Event {

    // [周一, 周二, 周三, 周四, 周五, 周六, 周日] 数目
    Integer[] weeklyHappened;

    public EventWeekly(Integer id, String name, Integer[] weeklyHappened) {
        super(id, name);
        this.weeklyHappened = weeklyHappened;
    }

    public Integer[] getWeeklyHappened() {
        return weeklyHappened;
    }

    public void setWeeklyHappened(Integer[] weeklyHappened) {
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
