package com.xspace.task.delay;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;

/**
 * 延迟任务：轮询
 */
@Slf4j
public class DelayTaskPollExample {
  private final static Map<String, Long> taskMap = Maps.newHashMap();

  public static void main(String[] args) {
    log.info("程序启动时间：{}", LocalDateTime.now());
    // 添加定时任务
    taskMap.put("task-1", Instant.now().plusSeconds(3).toEpochMilli()); // 延迟 3s

    // 调用无限循环实现延迟任务
    loopTask();
  }

  public static void loopTask() {
    Long itemLong = 0L;
    while (true) {
      Iterator it = taskMap.entrySet().iterator();
      while (it.hasNext()) {
        Map.Entry entry = (Map.Entry) it.next();
        itemLong = (Long) entry.getValue();
        // 有任务需要执行
        if (Instant.now().toEpochMilli() >= itemLong) {
          // 延迟任务，业务逻辑执行
          log.info("执行任务：{}, 执行时间：{}", entry.getKey(), LocalDateTime.now());
          // 删除任务
          taskMap.remove(entry.getKey());
        }
      }
    }
  }
}
