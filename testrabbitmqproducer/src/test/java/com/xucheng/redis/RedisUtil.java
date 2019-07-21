package com.xucheng.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: Nick zhang
 * @Date: 2019/7/19 15:10
 */
public class RedisUtil {

    //服务器地址
    private static String ADDR = "127.0.0.1";
    private static int PORT = 6379;
    //连接实例的最大连接数
    private static int MAX_ACTIVE = 1024;

    //设置一个pool中最多有多少个状态为idle(空闲)的jedis实例,默认是8.
    private static int MAX_IDLE = 200;
    //设置等待可用连接最大时间,单位为毫秒,默认是-1,表示用不超时,如果超过等待时间,则直接抛出JedisConnectionException
    private static int MAX_WAIT = 10000;
    //连接超时时间
    private static int TIMEOUT = 10000;
    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;


    private static JedisPool jedisPool = null;

    /**
     * 初始化
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config,ADDR,PORT,TIMEOUT,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取jedis实例
    public synchronized static Jedis getJedis(){
        try {
            if (jedisPool != null) {
                Jedis jedis = jedisPool.getResource();
                return jedis;
            } else {
                return null;
            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //释放资源
    public static void returnJedis(final Jedis jedis){
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }
}
