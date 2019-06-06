package com.zl.guava;

import com.google.common.base.*;
import lombok.extern.slf4j.Slf4j;

/**
 * guava中的字符串处理工具测试类
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2018-11-13 9:59
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Slf4j
public class GuavaStringUnitExample {
    public static void main(String[] args) {
        joinerTest1();
        joinerTest2();
        splitterTest1();
        charMatcherTest();
        charsetsTest();
    }

    /**
     * @return void
     * @throws
     * @Author zhanglei
     * @Description [字符串拼接]如果存在空值，替换为指定字符串
     * @Date 10:03 2018/11/13
     * @Param []
     **/
    private static void joinerTest1() {
        Joiner joiner = Joiner.on(";").useForNull("hello");
        System.out.println(joiner.join("Harry", null, "Ron", "Hermione"));
    }

    /**
     * @return void
     * @throws
     * @Author zhanglei
     * @Description [字符串拼接]如果存在null跳过该拼接
     * @Date 10:21 2018/11/13
     * @Param []
     **/
    private static void joinerTest2() {
        Joiner joiner = Joiner.on(";").skipNulls();
        System.out.println(joiner.join("Harry", null, "Ron", "Hermione"));
    }

    private static void splitterTest1() {
        Iterable<String> str = Splitter.on(',')
                .trimResults()          //移除结果字符串的前导空白和尾部空白
                .omitEmptyStrings()     //从结果中自动忽略空字符串
                .split("foo,bar,, qux");
        str.forEach(item -> System.out.println(item));
    }

    /**
     * @return void
     * @throws
     * @Author zhanglei
     * @Description 字符串匹配器
     * @Date 10:28 2018/11/13
     * @Param []
     **/
    private static void charMatcherTest() {
        /** 移除control字符 */
        String noControl = CharMatcher.JAVA_ISO_CONTROL.removeFrom("noControl00100");
        log.info(noControl);
        /** 去除两端的空格，并把中间的连续空格替换成单个空格*/
        String spaced = CharMatcher.WHITESPACE.trimAndCollapseFrom("  hello  word   ", ' ');
        log.info(spaced);
        /** 用*代替所有的数字 */
        String noDigits = CharMatcher.JAVA_DIGIT.replaceFrom("hello111-word", "*");
        log.info(noDigits);
        /** 只保留数字或小写字母 */
        String lowerAndDigit = CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE).retainFrom("WWC-123abc-''0");
        log.info(lowerAndDigit);

    }

    /**
     * @return void
     * @throws
     * @Description 字符集测试
     * @Author zhanglei
     * @Date 11:21 2018/11/13
     * @Param []
     **/
    private static void charsetsTest() {
        byte[] bytes = "hello".getBytes(Charsets.UTF_8);
        for (Byte byte0 : bytes) {
            log.info(byte0.toString());
        }
        /** 字母转小写并_后面首字母大写 */
        String str = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME_BB");
        log.info(str);
    }
}
