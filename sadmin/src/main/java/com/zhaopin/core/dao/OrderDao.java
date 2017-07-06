package com.zhaopin.core.dao;

import com.zhaopin.core.dto.order.OrderView;
import com.zhaopin.core.model.OrderModel;
import com.zhaopin.core.model.OrderReport;

import java.util.List;

/**
 * Created by zhou.hao on 2017/6/30.
 */
public interface OrderDao {
    public List<OrderModel> query(OrderView view);

    public int count();

    public OrderModel getOrderById(int oid);

    public OrderModel addOrderModel(OrderModel model);

    public OrderModel updateOrderModel(OrderModel model);

    /**
     * 对订单信息按照牧场和天汇总返回的map中 key为牧场id value为数据汇总
     *
     * @param day
     * @return
     */
    public List<OrderReport> countOrderByCustomerAndDay(String day);
}
