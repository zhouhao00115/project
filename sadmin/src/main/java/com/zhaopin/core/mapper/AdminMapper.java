package com.zhaopin.core.mapper;

import com.zhaopin.core.model.AdminModel;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Administrator on 2017/6/23.
 */
public interface AdminMapper {
    @Select("select id,username,password,power from user where username=#{username} order by id desc limit 0,1")
    AdminModel loginByUsername(String username);
}
