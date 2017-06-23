package com.zhaopin.core.service.impl;

import com.zhaopin.core.dao.AdminDao;
import com.zhaopin.core.model.AdminModel;
import com.zhaopin.core.service.AdminService;
import com.zhaopin.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/6/23.
 */
@Service("AdminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao dao;

    @Override
    public AdminModel loginByName(String username) {
        if(StringUtil.isNullOrEmpty(username)){
            return new AdminModel();
        }
        return dao.loginByName(username);
    }
}
