package com.zhaopin.core.dao.impl;

import com.zhaopin.core.dao.OrderDao;
import com.zhaopin.core.dbutil.DBFactory;
import com.zhaopin.core.dto.order.OrderView;
import com.zhaopin.core.mapper.CustomerMapper;
import com.zhaopin.core.mapper.OrderMapper;
import com.zhaopin.core.model.CustomerModel;
import com.zhaopin.core.model.OrderModel;
import com.zhaopin.core.model.OrderReport;
import com.zhaopin.core.util.StringUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhou.hao on 2017/6/30.
 */
@Repository("OrderDao")
public class OrderDaoImpl implements OrderDao {
    private SqlSessionFactory sqlSessionFactory = DBFactory.getSqlSessionFactory();

    @Override
    public List<OrderModel> query(OrderView view) {
        SqlSession session = sqlSessionFactory.openSession();
        List<OrderModel> orderModels = null;
        try {
            OrderMapper mapper = session.getMapper(OrderMapper.class);
            orderModels = mapper.query(view);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return orderModels;
    }

    @Override
    public int count() {
        SqlSession session = sqlSessionFactory.openSession();
        int number = 0;
        try {
            OrderMapper mapper = session.getMapper(OrderMapper.class);
            number = mapper.count();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return number;
    }

    @Override
    public OrderModel getOrderById(int oid) {
        SqlSession session = sqlSessionFactory.openSession();
        OrderModel orderModel = null;
        try {
            OrderMapper mapper = session.getMapper(OrderMapper.class);
            orderModel = mapper.getModelById(oid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return orderModel;
    }

    @Override
    public OrderModel addOrderModel(OrderModel model) {
        SqlSession session = sqlSessionFactory.openSession();
        OrderModel orderModel = null;
        try {
            OrderMapper mapper = session.getMapper(OrderMapper.class);
            CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);
            int count = mapper.count();
            int id;
            if (count > 0) {
                id = mapper.getLastId();
            } else {
                id = 0;
            }
            if (id >= 0) {
                model.setOid(id + 1);
                int rows = mapper.addOrders(model);
                if (rows > 0) {
                    orderModel = mapper.getModelById(model.getOid());
                    CustomerModel customerModel = customerMapper.getCustomerById(orderModel.getCustomerModel().getCid());
                    if (!StringUtil.isNullOrEmpty(customerModel.getCid())) {
                        customerModel.setLeft(customerModel.getLeft() + orderModel.getVolume());
                        int urows = customerMapper.updateCustomer(customerModel);
                        if (urows > 0) {
                            session.commit();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        if (null == orderModel) {
            orderModel = new OrderModel();
        }
        return orderModel;
    }

    @Override
    public OrderModel updateOrderModel(OrderModel model) {
        SqlSession session = sqlSessionFactory.openSession();
        OrderModel orderModel = null;
        try {
            OrderMapper mapper = session.getMapper(OrderMapper.class);
            OrderModel beforeChange = mapper.getModelById(model.getOid());
            CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);
            int rows = mapper.updateOrders(model);
            if (rows > 0) {
                orderModel = mapper.getModelById(model.getOid());
                CustomerModel customerModel = customerMapper.getCustomerById(orderModel.getCustomerModel().getCid());
                if (!StringUtil.isNullOrEmpty(customerModel.getCid())) {
                    customerModel.setLeft(customerModel.getLeft() + model.getVolume() - beforeChange.getVolume());
                    int urows = customerMapper.updateCustomer(customerModel);
                    if (urows > 0) {
                        session.commit();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return orderModel;
    }

    @Override
    public List<OrderReport>  countOrderByCustomerAndDay(String day) {
        System.out.println(day);
        SqlSession session = sqlSessionFactory.openSession();
        List<OrderReport> orderReports = null;
        try {
            OrderMapper mapper = session.getMapper(OrderMapper.class);
            orderReports = mapper.countOrder(day);
        } catch (Exception e) {
            e.printStackTrace();
            orderReports = new ArrayList<>();
        } finally {
            session.close();
        }
        return orderReports;
    }
}
