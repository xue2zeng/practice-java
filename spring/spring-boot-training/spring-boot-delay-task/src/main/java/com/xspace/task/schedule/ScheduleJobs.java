package com.xspace.task.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;

/**
 * 延迟任务：Spring 定时任务
 */
@Component
@Slf4j
public class ScheduleJobs {
  @Scheduled(fixedDelay = 2000)
  public void fixedDelayJob() {
    log.info("任务执行，时间：{}", DateFormat.getDateTimeInstance().format(new Date()));
  }
}
