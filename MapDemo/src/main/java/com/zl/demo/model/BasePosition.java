package com.zl.demo.model;

import java.io.Serializable;

/**
 * @Author zhanglei
 * @Description 坐标点
 * @Date 14:40 2018/10/11
 * @Param 
 * @return 
 **/
public class BasePosition implements Serializable {
    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 447441397550670531L;

    private double lng;

    private double lat;

    private long count;

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }





}
