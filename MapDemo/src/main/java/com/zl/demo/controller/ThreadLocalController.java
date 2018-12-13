package com.zl.demo.controller;

import com.zl.concurrency.annoations.ZlTest;
import com.zl.demo.model.Verification;
import com.zl.demo.test.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Set;

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
    @RequestMapping("test")
    @ResponseBody
    @ZlTest(param = "hello wold")
    public long test(){
        return RequestHolder.getId();
    }

    /**
     * @Author zhanglei
     * @Description 后端对参数的校验
     * @Date 16:11 2018/10/11
     * @Param [request, verification]
     * @return long
     **/
    @RequestMapping(value="/validator",method = RequestMethod.POST)
    @ResponseBody
    public long validator(HttpServletRequest request, @Valid @RequestBody Verification verification, BindingResult result){
        log.info("参数是：{}",verification.getName());
        log.info("参数是：{}",verification.getLength());
        if(result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
                log.info(error.getDefaultMessage());
            }
        }
        return RequestHolder.getId();
    }


    /**
     * @Author zhanglei
     * @Description 测试方法二
     * @Date 17:36 2018/10/11
     * @Param [request, verification]
     * @return long
     **/
    @RequestMapping(value="/validatorTwo",method = RequestMethod.POST)
    @ResponseBody
    public long validatorTwo(HttpServletRequest request,  @RequestBody Verification verification){
        log.info("参数是：{}",verification.getName());
        log.info("参数是：{}",verification.getLength());
        Set<ConstraintViolation<Verification>> constraintViolationSet = validator.validate(verification);
        for (ConstraintViolation<Verification> dem : constraintViolationSet) {
            log.info(dem.getMessage());
        }
        return RequestHolder.getId();
    }
}
