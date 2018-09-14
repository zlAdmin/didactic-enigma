package com.zl.cconcurrency.example.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试方法
 *
 * @author zhagnlei
 * @ProjectName: MapDemo
 * @create 2018-09-14 17:08
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2018</p>
 **/
@Slf4j
public class TestMe extends AbstractComm{
    private static int count = 0;

    public static void main(String[] args) {
        try {
            new TestMe().execute(5000,500);
            log.info("输出：{}",count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void definedMethods() {
        count++;
    }
}
