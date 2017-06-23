package com.zhaopin.core.dao.impl;

import com.zhaopin.core.dao.CustomerDao;
import com.zhaopin.core.dbutil.DBFactory;
import com.zhaopin.core.dto.customer.CustomerView;
import com.zhaopin.core.mapper.CustomerMapper;
import com.zhaopin.core.model.CustomerModel;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/6/20.
 */
@Repository("CustomerDao")
public class CustomerDaoImpl implements CustomerDao {
    private SqlSessionFactory sqlSessionFactory = DBFactory.getSqlSessionFactory();

    @Override
    public List<CustomerModel> query(CustomerView view) {
        SqlSession session = sqlSessionFactory.openSession();
        List<CustomerModel> customerModels = null;
        try {
            CustomerMapper mapper = session.getMapper(CustomerMapper.class);
            customerModels = mapper.query(view);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return customerModels;
    }

    @Override
    public CustomerModel getCustomerById(String customerId) {
        SqlSession session = sqlSessionFactory.openSession();
        CustomerModel customerModel = null;
        try {
            CustomerMapper mapper = session.getMapper(CustomerMapper.class);
            customerModel = mapper.getCustomerById(customerId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return customerModel;
    }
}
