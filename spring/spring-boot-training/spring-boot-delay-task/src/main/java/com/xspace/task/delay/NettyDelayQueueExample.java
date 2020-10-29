package com.xspace.task.delay;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 延迟任务：Netty
 */
@Slf4j
public class NettyDelayQueueExample {
  public static void main(String[] args) {
    log.info("开始时间：{}", DateFormat.getDateTimeInstance().format(new Date()));

    nettyTask();


  }

  /**
   * 基于Netty的延迟任务
   */
  public static void nettyTask() {
    // 创建延迟任务实例
    HashedWheelTimer timer = new HashedWheelTimer(3 , TimeUnit.SECONDS, 100); // 3：时间间隔 100：时间论中的槽数

    // 创建任务
    TimerTask task = new TimerTask() {
      @Override
      public void run(Timeout timeout) throws Exception {
        log.info("执行任务，执行时间：{}", DateFormat.getDateTimeInstance().format(new Date()));
      }
    };

    timer.newTimeout(task, 0, TimeUnit.SECONDS);
  }
}
