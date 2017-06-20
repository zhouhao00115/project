package com.zhaopin.core.mapper;

import com.zhaopin.core.model.CustomerModel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/6/20.
 */
public interface CustomerMapper {
    @Select("SELECR * FROM example")
    List<CustomerModel> query();
}
