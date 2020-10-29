package com.xspace.task.delay;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
  @Override
  public List<Order> selectFutureOverTimeOrder() {
    return Lists.newArrayList();
  }

  @Override
  public void updateCloseOverTimeOrder(String orderCode) {

  }
}
