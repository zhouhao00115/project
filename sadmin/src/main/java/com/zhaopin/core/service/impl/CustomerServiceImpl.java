package com.zhaopin.core.service.impl;

import com.zhaopin.core.conf.LocalSettings;
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
        List<CustomerModel> models = dao.query(view);
        for (CustomerModel model : models) {
            if (model.getLeft() < LocalSettings.lack) {
                model.setStaus(3);
                model.setStausdes("严重缺少");
            }
            if (LocalSettings.lack <= model.getLeft() && model.getLeft() < LocalSettings.enough) {
                model.setStaus(2);
                model.setStausdes("正常库存");
            }
            if (model.getLeft() >= LocalSettings.enough) {
                model.setStaus(1);
                model.setStausdes("库存充足");
            }
        }
        return models;
    }

    @Override
    public CustomerModel getModelById(CustomerView view) {
        if (StringUtil.isNullOrEmpty(view.getCustonerId())) {
            return new CustomerModel();
        }
        CustomerModel model = dao.getCustomerById(view.getCustonerId());
        if (model.getLeft() < LocalSettings.lack) {
            model.setStaus(3);
            model.setStausdes("严重缺少");
        }
        if (LocalSettings.lack <= model.getLeft() && model.getLeft() < LocalSettings.enough) {
            model.setStaus(2);
            model.setStausdes("正常库存");
        }
        if (model.getLeft() >= LocalSettings.enough) {
            model.setStaus(1);
            model.setStausdes("库存充足");
        }
        return model;
    }

    @Override
    public CustomerModel addCustomer(CustomerModel customerModel) {
        CustomerModel model = dao.addCustomer(customerModel);
        ;
        if (model.getLeft() < LocalSettings.lack) {
            model.setStaus(3);
            model.setStausdes("严重缺少");
        }
        if (LocalSettings.lack <= model.getLeft() && model.getLeft() < LocalSettings.enough) {
            model.setStaus(2);
            model.setStausdes("正常库存");
        }
        if (model.getLeft() >= LocalSettings.enough) {
            model.setStaus(1);
            model.setStausdes("库存充足");
        }
        return model;
    }

    @Override
    public int getCountCustomer() {
        return dao.getCountCustomer();
    }

    @Override
    public int deleteCustomerById(String cid) {
        if (StringUtil.isNullOrEmpty(cid)) {
            return 0;
        }
        return dao.deleteCustomerById(cid);
    }

    @Override
    public CustomerModel updateCustomer(CustomerModel customerModel) {
        CustomerModel model = dao.updateCustomer(customerModel);
        if (model.getLeft() < LocalSettings.lack) {
            model.setStaus(3);
            model.setStausdes("严重缺少");
        }
        if (LocalSettings.lack <= model.getLeft() && model.getLeft() < LocalSettings.enough) {
            model.setStaus(2);
            model.setStausdes("正常库存");
        }
        if (model.getLeft() >= LocalSettings.enough) {
            model.setStaus(1);
            model.setStausdes("库存充足");
        }
        return model;
    }
}
