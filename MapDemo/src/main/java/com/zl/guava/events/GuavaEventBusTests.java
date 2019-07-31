package com.zl.guava.events;

import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;

/**
 * main
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2019-07-31 16:10
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2019</p>
 **/
@Slf4j
public class GuavaEventBusTests {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        CustomEventListener customEventListener = new CustomEventListener();

        eventBus.register(customEventListener);

        eventBus.post(new CustomEvent("post a custom event ---- 1"));

        eventBus.unregister(customEventListener);

        eventBus.post(new CustomEvent("post a custom event ---- 2"));
    }
}
