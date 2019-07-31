package com.zl.guava.events;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 自定义消息
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2019-07-31 16:08
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2019</p>
 **/
@Data
@AllArgsConstructor
public class CustomEvent {
    private String message;
}
