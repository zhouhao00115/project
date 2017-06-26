package com.zhaopin.core.service;

import com.zhaopin.core.dto.datauser.DataUserView;
import com.zhaopin.core.model.DataUserModel;

import java.util.List;

/**
 * Created by Administrator on 2017/6/25.
 */
public interface DataUserService {
    public List<DataUserModel> query(DataUserView view);

    public DataUserModel getDataUserById(String staffid);

    public List<DataUserModel> allUser();

    public int count();

    public DataUserModel addUser(DataUserModel model);
}
