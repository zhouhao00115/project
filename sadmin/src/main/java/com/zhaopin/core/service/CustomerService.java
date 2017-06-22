package com.zhaopin.core.service;

import com.zhaopin.core.dto.customer.CustomerView;
import com.zhaopin.core.model.CustomerModel;

import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 */
public interface CustomerService {
    public List<CustomerModel> getList(CustomerView view);
}
