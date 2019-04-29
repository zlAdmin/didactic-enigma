package com.zl.reids;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * 通过哨兵池连接redis
 *
 * @author zhagnlei
 * @ProjectName: demo
 * @create 2019-03-19 21:17
 * @Version: 1.0
 * <p>Copyright: Copyright (zl) 2019</p>
 **/
public class JedisSentinel {
    public static void main(String[] args) {
        Set<String> ips = new HashSet<>();
        ips.add("127.0.0.1:26386");
        ips.add("127.0.0.1:26387");
        ips.add("127.0.0.1:26388");

        /**
         * 主节点服务名称，哨兵的ip的set集合
         */
        JedisSentinelPool sentinelPool = new JedisSentinelPool("redis001", ips);
        Jedis jedis = sentinelPool.getResource();
        jedis.auth("123456");

        //有对象后即可操作
    }
}
