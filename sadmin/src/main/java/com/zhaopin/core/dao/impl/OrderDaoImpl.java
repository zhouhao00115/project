package com.zhaopin.core.dao.impl;

import com.zhaopin.core.dao.OrderDao;
import com.zhaopin.core.dbutil.DBFactory;
import com.zhaopin.core.dto.order.OrderView;
import com.zhaopin.core.mapper.OrderMapper;
import com.zhaopin.core.model.OrderModel;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

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
            int id = mapper.getLastId();
            if (id > 0) {
                model.setOid(id + 1);
                int rows = mapper.addOrders(model);
                if (rows > 0) {
                    orderModel = mapper.getModelById(model.getOid());
                    session.commit();
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
            int rows = mapper.updateOrders(model);
            if(rows>0){
                orderModel = mapper.getModelById(model.getOid());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return orderModel;
    }
}
