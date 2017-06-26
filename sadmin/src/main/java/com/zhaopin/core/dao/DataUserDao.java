package com.zhaopin.core.dao;

import com.zhaopin.core.dto.datauser.DataUserView;
import com.zhaopin.core.model.DataUserModel;

import java.util.List;

/**
 * Created by Administrator on 2017/6/25.
 */
public interface DataUserDao {
    public List<DataUserModel> query(DataUserView view);

    public List<DataUserModel> allUser();

    public int count();

    public DataUserModel getDataUserById(int staffid);

    public DataUserModel addDataUser(DataUserModel model);

    public DataUserModel updateDataUser(DataUserModel model);
}
