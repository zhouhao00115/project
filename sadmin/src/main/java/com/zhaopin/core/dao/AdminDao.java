package com.zhaopin.core.dao;

import com.zhaopin.core.dto.admin.AdminCountDto;
import com.zhaopin.core.dto.admin.AdminView;
import com.zhaopin.core.model.AdminModel;

import java.util.List;

/**
 * Created by Administrator on 2017/6/23.
 */
public interface AdminDao {
    public List<AdminModel> query(AdminView view);

    public AdminModel loginByName(String username);

    public AdminCountDto count();

    public AdminModel getAdminById(int id);

    public AdminModel changeAdminModel(AdminModel model);

    public AdminModel addAdminModel(AdminModel model);
}
