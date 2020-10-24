package com.xspace.micro.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Objects;

/**
 * 时间工具类
 *
 * @author xue.zeng
 */
public class DateUtil {
  /**
   * 获取当前时间
   *
   * @return date
   */
  public static Date getCurrentTime() {
    LocalDateTime localDateTime = LocalDateTime.now();
    Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    return Date.from(instant);
  }

  /**
   * 格式化当前时间
   *
   * @param pattern the pattern to use to format the date, not null. For example,
   *                {@code yyyy-MM-dd HH:mm:ss}
   * @return the formatted date
   */
  public static String FormatCurrentDateTime(final String pattern) {
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
  }

  /**
   * 检查时间是否在指定的开始时间和结束时间区间
   *
   * @param currentTime
   * @param startTime
   * @param endTime
   * @return
   */
  public static Boolean checkTimeBetweenTime(Date currentTime, Date startTime, Date endTime) {
    if ((Objects.isNull(startTime) || startTime.before(currentTime))
            && (Objects.isNull(endTime) || endTime.after(currentTime))) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }

  /**
   * LocalDate类型转为Date
   *
   * @param localDate LocalDate object
   * @return Date object
   */
  public static Date localDate2Date(LocalDate localDate) {
    ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
    return Date.from(zonedDateTime.toInstant());
  }

  /**
   * LocalDateTime类型转为Date
   *
   * @param localDateTime LocalDateTime object
   * @return Date object
   */
  public static Date localDateTime2Date(LocalDateTime localDateTime) {
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }

  /**
   * 判断时间是否在本月之内
   *
   * @param date
   * @return
   */
  public static boolean isInThisMonth(Date date) {
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate now = LocalDate.now();
    return localDate.isBefore(now.with(TemporalAdjusters.lastDayOfMonth())) && localDate.isAfter(now.with(TemporalAdjusters.firstDayOfMonth()));
  }

  /**
   * 获得某天最大时间 2017-10-15 23:59:59
   * @param date
   * @return
   */
  public static Date getEndOfDay(Date date) {
    LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
    LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
    return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
  }

  /**
   * 获得某天最小时间 2017-10-15 00:00:00
   * @param date
   * @return
   */
  public static Date getStartOfDay(Date date) {
    LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
    LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
    return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
  }
}