package com.zhaopin.core.service;

import com.zhaopin.core.dto.order.OrderView;
import com.zhaopin.core.model.OrderModel;

import java.util.List;

/**
 * Created by zhou.hao on 2017/6/30.
 */
public interface OrderService {
    public List<OrderModel> query(OrderView view);

    public OrderModel getOrderById(String oid);

    public int count();

    public OrderModel addOrder(OrderModel model);
}
