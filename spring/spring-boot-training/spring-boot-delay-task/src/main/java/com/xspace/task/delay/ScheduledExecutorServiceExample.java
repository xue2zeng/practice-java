package com.xspace.task.delay;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 延迟任务：
 */
@Slf4j
public class ScheduledExecutorServiceExample {
  public static void main(String[] args) {
    // scheduledExecutorService1();
    // scheduledExecutorService2();
    scheduledExecutorService3();
  }

  public static void scheduledExecutorService1() {
    // 创建任务队列
    ScheduledExecutorService scheduledExecutorService =
            Executors.newScheduledThreadPool(10); // 10 为线程数量
    // 执行任务
    scheduledExecutorService.scheduleAtFixedRate(() -> {
      log.info("Run Schedule：{}", DateFormat.getDateTimeInstance().format(new Date()));
    }, 1, 3, TimeUnit.SECONDS); // 1s 后开始执行，每 3s 执行一次
  }

  /**
   * 任务超时执行测试
   * ScheduledExecutorService 解决 Timer 任务之间相应影响的缺点
   */
  public static void scheduledExecutorService2() {
    // 创建任务队列
    ScheduledExecutorService scheduledExecutorService =
            Executors.newScheduledThreadPool(10);
    // 执行任务 1
    scheduledExecutorService.scheduleAtFixedRate(() -> {
      log.info("进入 Schedule：" + new Date());
      try {
        // 休眠 5 秒
        TimeUnit.SECONDS.sleep(5);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      log.info("Run Schedule：{}", DateFormat.getDateTimeInstance().format(new Date()));
    }, 1, 3, TimeUnit.SECONDS); // 1s 后开始执行，每 3s 执行一次
    // 执行任务 2
    scheduledExecutorService.scheduleAtFixedRate(() -> {
      log.info("Run Schedule2：{}", DateFormat.getDateTimeInstance().format(new Date()));
    }, 1, 3, TimeUnit.SECONDS); // 1s 后开始执行，每 3s 执行一次
  }

  /**
   * 任务异常测试
   * ScheduledExecutorService 解决 Timer 任务异常影响其他任务的缺点
   */
  public static void scheduledExecutorService3() {
    // 创建任务队列
    ScheduledExecutorService scheduledExecutorService =
            Executors.newScheduledThreadPool(10);
    // 执行任务 1
    scheduledExecutorService.scheduleAtFixedRate(() -> {
      log.info("进入 Schedule：{}", DateFormat.getDateTimeInstance().format(new Date()));
      // 模拟异常
      int num = 8 / 0;
      log.info("Run Schedule：{}", DateFormat.getDateTimeInstance().format(new Date()));
    }, 1, 3, TimeUnit.SECONDS); // 1s 后开始执行，每 3s 执行一次
    // 执行任务 2
    scheduledExecutorService.scheduleAtFixedRate(() -> {
      log.info("Run Schedule2：{}", DateFormat.getDateTimeInstance().format(new Date()));
    }, 1, 3, TimeUnit.SECONDS); // 1s 后开始执行，每 3s 执行一次
  }
}
