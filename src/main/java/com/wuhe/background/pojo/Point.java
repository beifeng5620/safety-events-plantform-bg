package com.wuhe.background.pojo;

/**
 * @author wuhe
 * @Date 2020/4/30 - 16:35
 */
public class Point {
    Double lng;
    Double lat;

    public Point(Double lng, Double lat) {
        this.lng = lng;
        this.lat = lat;
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

    @Override
    public String toString() {
        return "Point{" +
                "lng=" + lng +
                ", lat=" + lat +
                '}';
    }
}
