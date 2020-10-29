package com.xspace.task.delay;

import com.xspace.task.util.LocalRedisUtil;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

/**
 * 延迟任务：Redis
 */
@Slf4j
public class RedisDelayQueueExample {
  // zset key
  private static final String _KEY = "myDelayQueue";

  // 订阅频道名称
  public static final String _TOPIC = "__keyevent@0__:expired";

  public static void main(String[] args) throws InterruptedException {
    // doZset();
    doNotifyKeyspaceEvent();
  }

  /**
   * 通过键空间通知
   * Redis 客户端执行：set test __keyevent@0__:expired ex 30
   */
  public static void doNotifyKeyspaceEvent() {
    Jedis jedis = LocalRedisUtil.getJedis();
    // 订阅过期消息
    jedis.psubscribe(new JedisPubSub() {
      @Override
      public void onPMessage(String pattern, String channel, String message) {
        // 接收到消息，执行定时任务
        log.info("{} 收到消息：{}", DateFormat.getDateTimeInstance().format(new Date()), message);
      }
    }, _TOPIC);
  }

  /**
   * 通过数据判断的方式
   * @throws InterruptedException
   */
  public static void doZset() throws InterruptedException {
    Jedis jedis = LocalRedisUtil.getJedis();
    // 延迟 30s 执行（30s 后的时间）
    long delayTime = Instant.now().plusSeconds(30).getEpochSecond();
    jedis.zadd(_KEY, delayTime, "order_1");
    // 继续添加测试数据
    jedis.zadd(_KEY, Instant.now().plusSeconds(2).getEpochSecond(), "order_2");
    jedis.zadd(_KEY, Instant.now().plusSeconds(2).getEpochSecond(), "order_3");
    jedis.zadd(_KEY, Instant.now().plusSeconds(7).getEpochSecond(), "order_4");
    jedis.zadd(_KEY, Instant.now().plusSeconds(10).getEpochSecond(), "order_5");
    // 开启延迟队列
    doDelayQueue(jedis);
  }

  /**
   * 延迟队列消费
   * @param jedis Redis 客户端
   */
  public static void doDelayQueue(Jedis jedis) throws InterruptedException {
    while (true) {
      // 当前时间
      Instant nowInstant = Instant.now();
      long lastSecond = nowInstant.plusSeconds(-1).getEpochSecond(); // 上一秒时间
      long nowSecond = nowInstant.getEpochSecond();
      // 查询当前时间的所有任务
      Set<String> data = jedis.zrangeByScore(_KEY, lastSecond, nowSecond);
      for (String item : data) {
        // 消费任务
        log.info("{} 开始消费：{}", DateFormat.getDateTimeInstance().format(new Date()), item);
      }
      // 删除已经执行的任务
      jedis.zremrangeByScore(_KEY, lastSecond, nowSecond);
      Thread.sleep(1000); // 每秒轮询一次
    }
  }
}
