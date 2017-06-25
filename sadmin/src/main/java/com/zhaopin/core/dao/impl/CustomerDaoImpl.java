package com.zhaopin.core.dao.impl;

import com.zhaopin.core.dao.CustomerDao;
import com.zhaopin.core.dbutil.DBFactory;
import com.zhaopin.core.dto.customer.CustomerView;
import com.zhaopin.core.mapper.CustomerMapper;
import com.zhaopin.core.model.CustomerModel;
import com.zhaopin.core.util.StringUtil;
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

    @Override
    public CustomerModel addCustomer(CustomerModel model) {
        SqlSession session = sqlSessionFactory.openSession();
        CustomerModel returnModel = null;
        try {
            CustomerMapper mapper = session.getMapper(CustomerMapper.class);
            String lastId = mapper.getLastId();
            int id = Integer.parseInt(lastId.substring(2, lastId.length()));
            id += 1;
            model.setCid("HS" + StringUtil.frontCompWithZore(id, 5));
            int rows = mapper.addCustomer(model);
            if(rows>0){
                returnModel = mapper.getCustomerById(model.getCid());
            }
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return returnModel;
    }

    @Override
    public int getCountCustomer() {
        SqlSession session = sqlSessionFactory.openSession();
        int number = 0;
        try {
            CustomerMapper mapper = session.getMapper(CustomerMapper.class);
            number = mapper.count();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return number;
    }


}
