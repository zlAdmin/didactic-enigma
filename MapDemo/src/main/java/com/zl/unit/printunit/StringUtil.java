package com.zl.unit.printunit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * String工具类
 *
 * @author zhanglei
 * @ProjectName: MapDemo
 * @create 2019-06-06 9:43
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2019</p>
 **/
public class StringUtil {
    private static final Logger log = LoggerFactory.getLogger(StringUtil.class);

    public StringUtil() {
    }


    /**
     * 是否空串
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        if (s == null || "".equals(s.trim())) {
            return true;
        }
        return false;
    }


    /**
     * 过滤 \r \n \t \s 等符号
     * @param str
     * @return
     */
    public static String filterBlankChars(String str){
        return filterChars(str, "\\s*|\t|\r|\n");
    }

    /**
     * 过滤字符串中的特殊字符
     * @param str 需要过滤的字符串
     * @param regex 过滤表达式，例：\\s*|\t|\r|\n 去掉 空格、回车、换行符、制表符
     * @return
     */
    public static String filterChars(String str, String regex){
        if(str == null){
            return null;
        }
        String content = new String(str);
        Pattern p = Pattern.compile(regex);
        return p.matcher(content).replaceAll("");
    }

    /**
     * 格式化金额，将小数位后三位变为小数位后两位
     * @param money
     * @return
     */
    public static String formatMoney(String money) {
        if(money == null){
            return "0.00";
        }
        if (money.indexOf(".") == -1) {
            money = money + ".000";
        }
        return money.substring(0, money.indexOf(".") + 3);
    }

    /**
     * 对象转化为字符串
     * null直接返回null
     * @Title: objToString
     * @param obj
     * @return
     */
    public static String objToString(Object obj) {
        if(null == obj){
            return null;
        }
        return String.valueOf(obj);
    }

    public static String objToString(Object obj,String defaultNullStr){
        if(null == obj){
            return defaultNullStr;
        }
        return String.valueOf(obj);
    }

    /**
     * 转义模糊(like)查询参数中出现的特殊字符（\ _ %）
     * @param parameter
     * @return
     */
    public static String escapeLikeQueryParameter(String parameter){

        return parameter.replaceAll("\\\\","\\\\\\\\")
                .replaceAll("\\_", "\\\\_")
                .replaceAll("\\%", "\\\\%");
    }

    public static String randomStr(int count){
        String allChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String result = "";
        for(int i=0;i<count;i++){
            Random random = new Random();
            int rand = random.nextInt(61);
            result +=allChars.charAt(rand);
        }

        return result;
    }

    public static String escapeMiniRichText(String str){
        str = str.replaceAll("(&rdquo;)|(&ldquo;)|(&deg;)|(&middot;)|(&mu;)|(&times;)|(&Phi;)|(&divide;)","");
        return str;
    }


    /**
     * 将明文中的手机号转换为138****6666格式
     * @param phone
     * @return
     */
    public static String telHide(String phone){
        if(!StringUtil.isEmpty(phone) && phone.length() > 3){
            String hiddenPhone = phone.substring(0,3)+"****"+phone.substring(phone.length() > 6 ? phone.length()-4:3,phone.length());
            return hiddenPhone;
        }
        return phone;
    }

    /**
     * 将明文中的邮箱转换成eam****@zl.com形式
     * @param email
     * @return
     */
    public static String eamilHide(String email){
        if(!StringUtil.isEmpty(email) && email.lastIndexOf("@") > 3){
            String hiddenEmail = email.substring(0,3)+"****"+email.substring(email.lastIndexOf("@"),email.length());
            return hiddenEmail;
        }
        return email;
    }

    public static String uuid(){
        return UUID.randomUUID()+"";
    }
}