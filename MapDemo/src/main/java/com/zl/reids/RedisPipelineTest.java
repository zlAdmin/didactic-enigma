package com.zl.reids;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @author zhagnlei
 * @ProjectName: demo
 * @create 2019-03-17 20:18
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2019</p>
 **/
public class RedisPipelineTest {
    public static Jedis jedis = new Jedis("localhost", 6379);



    public static void main(String[] args) {
        jedis.auth("123456");

        testSub();


    }

    public static void testPipeline(){
        int count = 10000;
        long timeMillis = System.currentTimeMillis();
        Pipeline pipeline = jedis.pipelined();
        for (int i = 0; i < count; i++) {
            pipeline.set("key" + i, "测试pipeline插入数据");
        }
        pipeline.sync();
        long endTime = System.currentTimeMillis();
        System.out.println("耗时" + (endTime - timeMillis) + "毫秒");
    }


    /**
     * 测试Redis的事务机制
     */
    public static void testRedisTr() {
        jedis.auth("123456");
        jedis.watch("key1");
        Transaction transaction = jedis.multi();

        jedis.set("key1", "hello");
        int c = 100/0;
        jedis.set("key2", "word");
        List<Object> exec = transaction.exec();
        if (exec.isEmpty()) {
            System.out.println("事务提交失败");
        }
        jedis.unwatch();
    }

    /**
     * 事务的实时场景测试
     */
    public static void restTr(){
        jedis.set("accountA", "100");
        jedis.set("accountB", "200");
        int count = 100;
        int accountA = Integer.parseInt(jedis.get("accountA"));
        if (accountA < count) {
            System.out.println("余额不足");
            return;
        } else {
            jedis.watch("accountA", "accountB");
            Transaction multi = jedis.multi();
            multi.decrBy("accountA", count);
            multi.incrBy("accountB", count);
            List<Object> exec = multi.exec();
            jedis.unwatch();
            if (exec.isEmpty()) {
                System.out.println("刷卡失败");
            } else {
                System.out.println("支付成功");
            }
        }
    }

    /**
     * 基于redis的MQ消息发送机制
     */
    public static void testPub() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            jedis.publish("zl", "hello" + i);
            Thread.sleep(1000);
        }
    }

    /**
     *  测试消息订阅
     */
    public static void testSub(){
        jedis.subscribe(new MyListener(), "zl");
    }

    /**
     * 内部类，负责处理消息
     */
    private static class MyListener extends  JedisPubSub {
        @Override
        public void onMessage(String channel, String message) {
            System.out.println(channel + ":" + message);
        }
    }


}

