package com.zl.demo.controller;

import com.zl.concurrency.annoations.ZlTest;
import com.zl.demo.test.RequestHolder;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Validator;

/**
 * ThreadLocal验证
 *
 * @author zhagnlei
 * @ProjectName: zlAdmin
 * @create 2018-09-16 12:31
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Slf4j
@Controller
@RequestMapping("threadLocal")
public class ThreadLocalController {
    @Autowired
    private Validator validator;
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    @ZlTest(param = "#id")
    public long test(@NonNull String id){
        return RequestHolder.getId();
    }
}
