package com.zl.demo.aspect;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zl.concurrency.annoations.ZlTest;
import com.zl.demo.unit.SpelParser;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 定义一个切面，用于统计指定注解的方法调用时间
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2018-12-13 10:08
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Component
@Aspect
@Slf4j
public class ZlAspect {

    ThreadLocal<Long> beginTime = new ThreadLocal<>();

    @Pointcut("@annotation(zlTest)")
    public void serviceStatistics(ZlTest zlTest) {
    }

    /** 前置增强 */
   /* @Before("serviceStatistics(zlTest)")
    public void doBefore(JoinPoint joinPoint, ZlTest zlTest) {
        // 记录请求到达时间
        beginTime.set(System.currentTimeMillis());
        Object[] args = joinPoint.getArgs();

        args[0] = "这里是我获取的参数";
        log.info("请求到达，参数是:{}", zlTest.param());
    }*/

    /** 获取方法返回值，但是无法修改返回值 */
  /*  @AfterReturning(returning="rvt", pointcut ="serviceStatistics(zlTest)")
    public void doAfterReturning (Object rvt,ZlTest zlTest) {
        log.info("参数返回:{}",rvt);
    }*/

    /** 后置增强 */
/*
    @After("serviceStatistics(zlTest)")
    public void doAfter(ZlTest zlTest) {
        log.info("请求完毕，执行时间是:{}", System.currentTimeMillis() - beginTime.get());
    }*/

    @Around("serviceStatistics(zlTest)")
    public Object doAround(ProceedingJoinPoint joinPoint, ZlTest zlTest) throws Throwable {
        System.out.println("beginning----");
        /**
         * 获取方法参数值
         */
        String key = getKey(zlTest.param(), joinPoint);
        log.info("参数是：{}",key);


        /**
         * 运行doSth()，执行方法的具体业务，并获取返回值，用一个Object类型来接收
         */
        Object object = joinPoint.proceed();

        /**
         * 该方法的后置增强，可以修改返回值
         */
        object = 10;
        return object;
    }

    private String getKey(String param, ProceedingJoinPoint joinPoint) {
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        //获取方法的形参
        String [] parameterNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(method);
        return SpelParser.getKey(param, "", parameterNames, joinPoint.getArgs());
    }

    private void sysTemOut (String str) {
        JSONObject jsStr = JSONObject.parseObject(str);
        JSONArray jsonResultArray = (JSONArray) jsStr.get("result");
        for(int i=0;i<jsonResultArray.size();i++){
            JSONObject jsonResultData=(JSONObject)jsonResultArray.get(i);
            jsonResultData.remove("solutionCount");
        }
        log.info("结果是：{}",JSONObject.toJSONString(jsStr));
    }

}
