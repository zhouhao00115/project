package com.zhaopin.core.mapper;

import com.zhaopin.core.dto.customer.CustomerView;
import com.zhaopin.core.mapper.provider.CustomerProvider;
import com.zhaopin.core.model.CustomerModel;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by Administrator on 2017/6/20.
 */
public interface CustomerMapper {
    @SelectProvider(method = "getQuerySql", type = CustomerProvider.class)
    List<CustomerModel> query(CustomerView view);

    @Select("select * from customer as t1,(select staffid,sname,sphone from staff) as t2 where t1.cid=#{customerId} " +
            "AND t1.staffid=t2.staffid order by cid desc limit 0,1")
    CustomerModel getCustomerById(String customerId);
}
