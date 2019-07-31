package com.zl.guava.events;

import com.google.common.eventbus.Subscribe;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 自定义监听
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2019-07-31 16:09
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2019</p>
 **/

@Slf4j
@Data
@AllArgsConstructor
public class CustomEventListener {

    @Subscribe
    public void onEvent(CustomEvent event) {
        log.info("Guava EventListener listened one message : {}", event.getMessage());
    }
}
