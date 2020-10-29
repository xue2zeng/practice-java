package com.xspace.task.delay;

import java.util.List;

public interface OrderService {
  List<Order> selectFutureOverTimeOrder();

  void updateCloseOverTimeOrder(String orderCode);
}
