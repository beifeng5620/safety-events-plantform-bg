package com.wuhe.background.view;

/**
 * @author wuhe
 * @Date 2020/4/30 - 16:53
 */
public class RectQueryView {
    // 左上角和右下角经纬度
    Double tlLng;
    Double tlLat;
    Double brLng;
    Double brLat;

    public RectQueryView(Double tlLng, Double tlLat, Double brLng, Double brLat) {
        this.tlLng = tlLng;
        this.tlLat = tlLat;
        this.brLng = brLng;
        this.brLat = brLat;
    }

    public Double getTlLng() {
        return tlLng;
    }

    public void setTlLng(Double tlLng) {
        this.tlLng = tlLng;
    }

    public Double getTlLat() {
        return tlLat;
    }

    public void setTlLat(Double tlLat) {
        this.tlLat = tlLat;
    }

    public Double getBrLng() {
        return brLng;
    }

    public void setBrLng(Double brLng) {
        this.brLng = brLng;
    }

    public Double getBrLat() {
        return brLat;
    }

    public void setBrLat(Double brLat) {
        this.brLat = brLat;
    }

    @Override
    public String toString() {
        return "RectQueryView{" +
                "tlLng=" + tlLng +
                ", tlLat=" + tlLat +
                ", brLng=" + brLng +
                ", brLat=" + brLat +
                '}';
    }

    /**
     * 判断入参是否是null
     * @return
     */
    public  boolean isEmpty() {
        if (null == this.tlLng || null == this.tlLat || null == this.brLng || null == this.brLat){
            return true;
        } else {
            return false;
        }
    }
}
