package com.zl.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.val;

import java.io.Serializable;

/**
 * @Author zhanglei
 * @Description 坐标点
 * @Date 14:40 2018/10/11
 * @Param 
 * @return 
 **/
@Data
public class BasePosition implements Serializable {
    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 447441397550670531L;

    private double lng;

    private double lat;

    private long count;

}
