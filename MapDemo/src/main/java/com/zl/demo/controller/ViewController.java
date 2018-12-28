package com.zl.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zl.demo.model.BasePosition;
import com.zl.demo.service.ResultBeanService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.concurrent.*;

@RestController
@RequestMapping(value = "/show")
@Slf4j
public class ViewController {
    @Autowired
    private ResultBeanService resultService;

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    @RequestMapping(value="/map",method = RequestMethod.GET)
    public ModelAndView map() throws InterruptedException {
        Thread.sleep(5000);
        ModelAndView view = new ModelAndView("map");
        List<BasePosition> list  = resultService.query();
        String json= JSONObject.toJSONString(list);
        JSONArray subMsgs  = JSONObject.parseArray(json);
        System.out.print(subMsgs);
        view.addObject("data_json",subMsgs) ;
        return view;
    }

    @RequestMapping(value="/maprefresh",method = RequestMethod.GET)
    public ModelAndView maprefresh() {
        ModelAndView view = new ModelAndView("maprefresh");
        return view;
    }



    /**
     * @Description 基于Ajax长连接，实现主动通知web应用
     * @return 
     * @throws 
     * @Author zhanglei
     * @Date 13:48 2018/12/28
     * @Param
     **/
    @RequestMapping(value="/getPosition",method = RequestMethod.GET)
    public  DeferredResult<JSONArray> getPosition() throws ExecutionException, InterruptedException {
        DeferredResult<JSONArray> deferredResult = new DeferredResult<>();
        log.info("主线程执行...");
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                log.info("从线程开始执行...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<BasePosition> positionList  = resultService.query();
                String json= JSONObject.toJSONString(positionList);
                JSONArray subMsgs  = JSONObject.parseArray(json);
                deferredResult.setResult(subMsgs);
                log.info("从线程执行完毕...");
            }
        });
        log.info("主线程执行完毕....");
        return deferredResult;
    }

    @RequestMapping(value="/mapstat",method = RequestMethod.GET)
    public ModelAndView mapstat() {
        System.out.println("hello");
        List<BasePosition> list  = resultService.query();

        System.out.print(list.size());

        return new ModelAndView("mapstat");

    }

    /**
     * @Description 解决的问题：tomcat内的线程数量是有限度最大1000个，如果方法过久会导致main线程持续占用，
     * 当有新任务到达时分配不到线程将处于阻塞状态
     * @return java.util.concurrent.Callable<java.util.List<com.zl.demo.model.BasePosition>>
     * @throws 
     * @Author zhanglei
     * @Date 14:54 2018/12/28
     * @Param []
     **/
    @RequestMapping(value="/mapstatAsync",method = RequestMethod.POST)
    public Callable<List<BasePosition>> mapstatAsync() {
        log.info("主线程开始...");
        Callable<List<BasePosition>>  callable = new Callable<List<BasePosition>>() {
            @Override
            public List<BasePosition> call() throws Exception {
                log.info("从线程开始...");
                Thread.sleep(5000);
                val list  = resultService.query();
                log.info("从线程结束...");
                return list;
            }
        };
        log.info("主线程结束...");
        return callable;

    }
}
