package com.zl.demo.unit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Spel解析
 *
 * @author zhagnlei
 * @ProjectName: demo
 * @create 2018-12-13 22:13
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2018</p>
 **/
@Slf4j
public class SpelParser {
    private static ExpressionParser parser = new SpelExpressionParser();

    public static String getKey(String key, String condition, String[] paramNames, Object[] arguments) {
        try {
            if (!checkCondition(condition, paramNames, arguments)) {
                return null;
            }
            Expression expression = parser.parseExpression(key);
            EvaluationContext context = new StandardEvaluationContext();
            int length = paramNames.length;
            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    context.setVariable(paramNames[i], arguments[i]);
                }
            }
            return expression.getValue(context, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean checkCondition(String condition, String[] paramNames, Object[] arguments) {
        if (condition.length() < 1) {
            return true;
        }
        Expression expression = parser.parseExpression(condition);
        EvaluationContext context = new StandardEvaluationContext();
        int length = paramNames.length;
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                context.setVariable(paramNames[i], arguments[i]);
            }
        }
        return expression.getValue(context, boolean.class);
    }

    public static void main(String[] args) {
        String key = "'getAudioListByBIdNo.'+#bIdNo";
        System.out.println(getKey(key, "1232", new String[]{"bIdNo"}, new Object[]{"B19001084"}));
    }
}