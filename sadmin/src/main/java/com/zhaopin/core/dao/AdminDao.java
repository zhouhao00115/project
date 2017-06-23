package com.zhaopin.core.dao;

import com.zhaopin.core.model.AdminModel;

/**
 * Created by Administrator on 2017/6/23.
 */
public interface AdminDao {
    public AdminModel loginByName(String username);
}
