package com.zhaopin.core.mapper;

import com.zhaopin.core.dto.admin.AdminCountDto;
import com.zhaopin.core.dto.admin.AdminView;
import com.zhaopin.core.mapper.provider.AdminProvider;
import com.zhaopin.core.model.AdminModel;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by Administrator on 2017/6/23.
 */
public interface AdminMapper {
    @SelectProvider(method = "getQuerySql", type = AdminProvider.class)
    List<AdminModel> query(AdminView view);

    @Select("select id,username,password,power from user where username=#{username} order by id desc limit 0,1")
    AdminModel loginByUsername(String username);

    @Select("select count(*) from user")
    int countAll();

    @Select("select count(*) from user where power=1")
    int countAdmin();

    @Select("select count(*) from user where power=2")
    int countread();
}
