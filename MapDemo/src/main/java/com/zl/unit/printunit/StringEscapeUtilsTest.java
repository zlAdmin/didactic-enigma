package com.zl.unit.printunit;
import org.apache.commons.text.StringEscapeUtils;

/**
 * 字符串的工具StringEscapeUtils使用测试
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2019-07-12 16:30
 * @Version: 1.0
 * <p>Copyright: Copyright (acmtc) 2019</p>
 **/
public class StringEscapeUtilsTest {
    public static void main(String[] args) {

        //转义成Unicode编码
        System.out.println("转成Unicode编码："+StringEscapeUtils.escapeJava("陈磊兴"));

        //转义xml
        System.out.println("反转义XML："+StringEscapeUtils.unescapeXml("<name>陈磊兴</name>"));

        String str = StringEscapeUtils.unescapeHtml4("&lt;p&gt;【产品名称】艾酷维多种维生素锌软糖&lt;/p&gt;");
        System.out.println(str);
        String str2 = StringEscapeUtils.escapeHtml4("<p>【产品名称】艾酷维多种维生素锌软糖</p>");
        System.out.println(str2);
    }
}
