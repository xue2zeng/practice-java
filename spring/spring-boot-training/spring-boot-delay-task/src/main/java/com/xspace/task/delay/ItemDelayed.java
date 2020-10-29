package com.xspace.task.delay;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟任务：JDK DelayQueue 数据延迟实现实例 用以包装具体的实例转型
 */

@Data
public class ItemDelayed<T> implements Delayed {
  /**默认延迟30分钟*/
  private final static long DELAY_TIME = 30 * 60 * 1000L;
  /**数据id*/
  private String dataId;
  /**开始时间*/
  private long startTime;
  /**到期时间*/
  private long expire;
  /**创建时间*/
  private LocalDateTime createTime;
  /**泛型data*/
  private T data;

  public ItemDelayed(String dataId, long startTime, long secondsDelay) {
    super();
    this.dataId = dataId;
    this.startTime = startTime;
    this.expire = startTime + (secondsDelay * 1000);
    this.createTime = LocalDateTime.now();
  }

  public ItemDelayed(String dataId, long startTime) {
    super();
    this.dataId = dataId;
    this.startTime = startTime;
    this.expire = startTime + DELAY_TIME;
    this.createTime = LocalDateTime.now();
  }

  @Override
  public long getDelay(TimeUnit unit) {
    //消息是否到期（是否可以被读取出来）判断的依据。当返回负数，说明消息已到期，此时消息就可以被读取出来了
    return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
  }

  @Override
  public int compareTo(Delayed o) {
    //这里根据延迟任务时间来比较，如果执行任务时间小的，就会优先被队列提取出来
    return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
  }
}
