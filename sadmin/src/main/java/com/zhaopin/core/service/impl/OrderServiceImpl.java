package com.zhaopin.core.service.impl;

import com.zhaopin.core.dao.OrderDao;
import com.zhaopin.core.dto.order.OrderView;
import com.zhaopin.core.model.OrderModel;
import com.zhaopin.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhou.hao on 2017/6/30.
 */
@Service("OrderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public List<OrderModel> query(OrderView view) {
        return orderDao.query(view);
    }

    @Override
    public OrderModel getDataUserById(String oid) {
        int number = 0;
        try {
            number = Integer.parseInt(oid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (number > 0) {
            return orderDao.getOrderById(number);
        }
        return new OrderModel();
    }

    @Override
    public int count() {
        return orderDao.count();
    }

    @Override
    public OrderModel addOrder(OrderModel model) {
        if (model.getOid() > 0) {
            return orderDao.updateOrderModel(model);
        }
        return orderDao.addOrderModel(model);
    }
}
