package com.xspace.task.util;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;
import java.util.Set;

@Slf4j
public class LocalRedisUtil {
  private static JedisPool jedisPool = null;

  static {
    //使用ResourceBundle类读取配置文件
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    //拿到数据信息
    String host = resourceBundle.getString("spring.redis.host");
    int port = Integer.parseInt(resourceBundle.getString("spring.redis.port"));
    int maxTotal = Integer.parseInt(resourceBundle.getString("spring.redis.pool.max-idle"));
    int maxIdle = Integer.parseInt(resourceBundle.getString("spring.redis.pool.min-idle"));
    //设置配置信息
    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    jedisPoolConfig.setMaxIdle(maxIdle);
    jedisPoolConfig.setMaxTotal(maxTotal);
    //初始化
    jedisPool = new JedisPool(jedisPoolConfig, host, port);
  }

  //获取redis操作对象
  public static Jedis getJedis() {
    return jedisPool.getResource();
  }

  /**
   * <p>
   * 通过key返回指定score内zset中的value
   * </p>
   *
   * @param key
   * @param max
   * @param min
   * @return
   */
  public static Set<String> zrangeByScore(String key, String max, String min) {
    Jedis jedis = null;
    Set<String> res = null;
    try {
      jedis = jedisPool.getResource();
      res = jedis.zrevrangeByScore(key, max, min);
    } catch (Exception e) {
      log.error(e.getMessage());
    } finally {
      returnResource(jedisPool, jedis);
    }
    return res;
  }

  /**
   * 返还到连接池
   *
   * @param jedisPool
   * @param jedis
   */
  public static void returnResource(JedisPool jedisPool, Jedis jedis) {
    if (jedis != null) {
      jedisPool.close();
    }
  }
}
