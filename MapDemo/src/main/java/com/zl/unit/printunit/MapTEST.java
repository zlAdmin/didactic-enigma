package com.zl.unit.printunit;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Var;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhagnlei
 * @ProjectName: demo
 * @create 2019-01-07 21:09
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2019</p>
 **/

@Slf4j
public class MapTEST {
    public static void main(String[] args) {
        Map map = new HashMap<String,String>(16);
        System.out.println(map.size());

    }
}
