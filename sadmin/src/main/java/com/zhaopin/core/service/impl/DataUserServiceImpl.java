package com.zhaopin.core.service.impl;

import com.zhaopin.core.dao.DataUserDao;
import com.zhaopin.core.dto.datauser.DataUserView;
import com.zhaopin.core.model.DataUserModel;
import com.zhaopin.core.service.DataUserService;
import com.zhaopin.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/6/25.
 */
@Service("DataUserService")
public class DataUserServiceImpl implements DataUserService {
    @Autowired
    private DataUserDao dao;

    @Override
    public List<DataUserModel> query(DataUserView view) {
        return dao.query(view);
    }

    @Override
    public DataUserModel getDataUserById(String staffid) {
        if (StringUtil.isNullOrEmpty(staffid)) {
            return new DataUserModel();
        }
        int number = 0;
        try {
            number = Integer.parseInt(staffid);
        } catch (Exception e) {
            e.printStackTrace();
            return new DataUserModel();
        }
        return dao.getDataUserById(number);
    }

    @Override
    public List<DataUserModel> allUser() {
        return dao.allUser();
    }

    @Override
    public int count() {
        return dao.count();
    }

    @Override
    public DataUserModel addUser(DataUserModel model) {
        if (model.getStaffid() > 0) {
            return dao.updateDataUser(model);
        }
        return dao.addDataUser(model);
    }
}
