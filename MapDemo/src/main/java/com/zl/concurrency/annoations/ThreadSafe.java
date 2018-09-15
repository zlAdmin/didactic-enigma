package com.zl.concurrency.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * java类简单作用描述
 *
 * @ProjectName: MapDemo
 * @Package: com.zl.concurrency.annoations
 * @ClassName: ${TYPE_NAME}
 * @Description: 线程安全类注解(只是标记)
 * @Author: zhanglie
 * @CreateDate: 2018/9/10 23:06
 * @UpdateUser: Zhanglei
 * @UpdateDate: 2018/9/10 23:06
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadSafe {
    String value() default  "";
}
