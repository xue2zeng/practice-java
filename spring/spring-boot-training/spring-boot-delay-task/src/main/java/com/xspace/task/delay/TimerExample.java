package com.xspace.task.delay;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * 延迟任务：Timer
 */
@Slf4j
public class TimerExample {
  public static void main(String[] args) {
    // timerTask1();
    // timerTask2();
    timerTask3();
  }

  public static void timerTask1() {
    // 定义任务
    TimerTask timerTask = new TimerTask() {
      @Override
      public void run() {
        log.info("Run timer task: {}", DateFormat.getDateTimeInstance().format(new Date()));
      }
    };

    // 计时器
    Timer timer = new Timer();
    // 添加执行任务：延迟1s执行，每3s执行一次
    timer.schedule(timerTask, 1000, 3000);
  }

  /**
   * 问题1：任务执行时间长影响其他任务
   */
  public static void timerTask2() {
    // 定义任务1
    TimerTask timerTask1 = new TimerTask() {
      @Override
      public void run() {
        log.info("Enter timer task1: {}", DateFormat.getDateTimeInstance().format(new Date()));
        try {
          TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
          log.error("task fail.", e);
        }
        log.info("Run timer task1: {}", DateFormat.getDateTimeInstance().format(new Date()));
      }
    };

    // 定义任务2
    TimerTask timerTask2 = new TimerTask() {
      @Override
      public void run() {
        log.info("Run timer task2: {}", DateFormat.getDateTimeInstance().format(new Date()));
      }
    };

    // 计时器
    Timer timer = new Timer();
    // 添加执行任务：延迟1s执行，每3s执行一次
    timer.schedule(timerTask1, 1000, 3000);
    timer.schedule(timerTask2, 1000, 3000);
  }

  /**
   * 问题 2：任务异常影响其他任务
   */
  public static void timerTask3() {
    // 定义任务1
    TimerTask timerTask1 = new TimerTask() {
      @Override
      public void run() {
        log.info("Enter timer task1: {}", DateFormat.getDateTimeInstance().format(new Date()));
        int num = 8 / 0;
        log.info("Run timer task1: {}", DateFormat.getDateTimeInstance().format(new Date()));
      }
    };

    // 定义任务2
    TimerTask timerTask2 = new TimerTask() {
      @Override
      public void run() {
        log.info("Run timer task2: {}", DateFormat.getDateTimeInstance().format(new Date()));
      }
    };

    // 计时器
    Timer timer = new Timer();
    // 添加执行任务：延迟1s执行，每3s执行一次
    timer.schedule(timerTask1, 1000, 3000);
    timer.schedule(timerTask2, 1000, 3000);
  }
}
