package com.zl.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zl.demo.model.BasePosition;
import com.zl.demo.service.ResultBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.concurrent.Callable;

@RestController
public class ViewController {
    @Autowired
    private ResultBeanService resultService;


    @RequestMapping(value="/map",method = RequestMethod.GET)
    public ModelAndView map() {
        ModelAndView view = new ModelAndView("map");
        List<BasePosition> list  = resultService.query();
        String json= JSONObject.toJSONString(list);
        JSONArray subMsgs  = JSONObject.parseArray(json);
        System.out.print(subMsgs);
        view.addObject("data_json",subMsgs);
        return view;

    }
    @RequestMapping(value="/mapstat",method = RequestMethod.GET)
    public ModelAndView mapstat() {
        System.out.println("hello");
        List<BasePosition> list  = resultService.query();

        System.out.print(list.size());

        return new ModelAndView("mapstat");

    }

    @RequestMapping(value="/mapstatAsync",method = RequestMethod.POST)
    public Callable<List<BasePosition>> mapstatAsync() {
        System.out.print("");
        Callable<List<BasePosition>>  callable = new Callable<List<BasePosition>>() {
            @Override
            public List<BasePosition> call() throws Exception {
                List<BasePosition> list  = resultService.query();
                return list;
            }
        };
        return callable;

    }
}
