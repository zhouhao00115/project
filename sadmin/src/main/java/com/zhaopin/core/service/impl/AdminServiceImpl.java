package com.zhaopin.core.service.impl;

import com.zhaopin.core.dao.AdminDao;
import com.zhaopin.core.dto.admin.AdminCountDto;
import com.zhaopin.core.dto.admin.AdminView;
import com.zhaopin.core.model.AdminModel;
import com.zhaopin.core.service.AdminService;
import com.zhaopin.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/23.
 */
@Service("AdminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao dao;

    @Override
    public AdminModel loginByName(String username) {
        if (StringUtil.isNullOrEmpty(username)) {
            return new AdminModel();
        }
        return dao.loginByName(username);
    }

    @Override
    public AdminCountDto getCountAdmin() {
        return dao.count();
    }

    @Override
    public List<AdminModel> getList(AdminView view) {
        if (view.getId() > 0) {
            return new ArrayList<>();
        }
        return dao.query(view);
    }
}
