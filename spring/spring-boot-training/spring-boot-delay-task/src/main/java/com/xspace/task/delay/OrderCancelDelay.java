package com.xspace.task.delay;

/**
 * 延迟队列：订单取消
 */
public interface OrderCancelDelay<T> {
  /**
   * 添加延迟对象到延时队列
   *
   * @param itemDelayed 延迟对象
   * @return boolean
   */
  boolean addToOrderDelayQueue(ItemDelayed<T> itemDelayed);

  /**
   * 根据对象添加到指定延时队列
   *
   * @param data 数据对象
   * @return boolean
   */
  boolean addToDelayQueue(T data);

  /**
   * 从延时队列中移除指定的延迟对象
   *
   * @param data
   */
  void removeToOrderDelayQueue(T data);
}
