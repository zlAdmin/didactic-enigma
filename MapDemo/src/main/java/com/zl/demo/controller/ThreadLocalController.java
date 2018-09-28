package com.zl.demo.controller;

import com.zl.demo.test.RequestHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ThreadLocal验证
 *
 * @author zhagnlei
 * @ProjectName: zlAdmin
 * @create 2018-09-16 12:31
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Controller
@RequestMapping("threadLocal")
public class ThreadLocalController {
    @RequestMapping("test")
    @ResponseBody
    public long test(){
        return RequestHolder.getId();
    }
}
