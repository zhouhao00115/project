package com.zhaopin.core.service.impl;

import com.zhaopin.core.dao.CustomerDao;
import com.zhaopin.core.dto.customer.CustomerView;
import com.zhaopin.core.model.CustomerModel;
import com.zhaopin.core.service.CustomerService;
import com.zhaopin.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 */
@Service("CustomerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao dao;

    @Override
    public List<CustomerModel> getList(CustomerView view) {
        return dao.query(view);
    }

    @Override
    public CustomerModel getModelById(CustomerView view) {
        if (StringUtil.isNullOrEmpty(view.getCustonerId())) {
            return new CustomerModel();
        }
        return dao.getCustomerById(view.getCustonerId());
    }

    @Override
    public CustomerModel addCustomer(CustomerModel model) {
        return dao.addCustomer(model);
    }

    @Override
    public int getCountCustomer() {
        return dao.getCountCustomer();
    }

    @Override
    public int deleteCustomerById(String cid) {
        if(StringUtil.isNullOrEmpty(cid)){
            return 0;
        }
        return dao.deleteCustomerById(cid);
    }

    @Override
    public CustomerModel updateCustomer(CustomerModel model) {
        return dao.updateCustomer(model);
    }
}
