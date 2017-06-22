package com.zhaopin.core.dao;

import com.zhaopin.core.dto.customer.CustomerView;
import com.zhaopin.core.model.CustomerModel;

import java.util.List;

/**
 * Created by Administrator on 2017/6/20.
 */
public interface CustomerDao {
    public List<CustomerModel> query(CustomerView view);
}
