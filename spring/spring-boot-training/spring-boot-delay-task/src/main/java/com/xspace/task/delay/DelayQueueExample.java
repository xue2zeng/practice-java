package com.xspace.task.delay;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟任务：JDK DelayQueue
 */
@Slf4j
public class DelayQueueExample {
  public static void main(String[] args) throws InterruptedException {
    DelayQueue delayQueue = new DelayQueue();

    delayQueue.put(new DelayElement(1000));
    delayQueue.put(new DelayElement(2000));
    delayQueue.put(new DelayElement(3000));

    log.info("开始时间：{}", DateFormat.getDateTimeInstance().format(new Date()));
    while (!delayQueue.isEmpty()) {
      log.info("开始执行：{}", delayQueue.take());
    }
    log.info("结束时间：{}", DateFormat.getDateTimeInstance().format(new Date()));
  }

  static class DelayElement implements Delayed {
    long delayTime = System.currentTimeMillis();

    public DelayElement(long delayTime) {
      this.delayTime = this.delayTime + delayTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
      return unit.convert(delayTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
      return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
      return DateFormat.getDateTimeInstance().format(new Date(delayTime));
    }
  }
}
