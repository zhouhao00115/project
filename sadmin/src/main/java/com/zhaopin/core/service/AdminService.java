package com.zhaopin.core.service;

import com.zhaopin.core.dto.admin.AdminCountDto;
import com.zhaopin.core.dto.admin.AdminView;
import com.zhaopin.core.model.AdminModel;

import java.util.List;

/**
 * Created by Administrator on 2017/6/23.
 */
public interface AdminService {
    public AdminModel loginByName(String username);
    public AdminCountDto getCountAdmin();
    public List<AdminModel> getList(AdminView view);
}
