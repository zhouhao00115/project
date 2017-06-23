package com.zhaopin.core.dao.impl;

import com.zhaopin.core.dao.AdminDao;
import com.zhaopin.core.dbutil.DBFactory;
import com.zhaopin.core.mapper.AdminMapper;
import com.zhaopin.core.mapper.CustomerMapper;
import com.zhaopin.core.model.AdminModel;
import com.zhaopin.core.model.CustomerModel;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/6/23.
 */
@Repository("AdminDao")
public class AdminDaoImpl implements AdminDao {

    private SqlSessionFactory sqlSessionFactory = DBFactory.getSqlSessionFactory();

    @Override
    public AdminModel loginByName(String username) {
        SqlSession session = sqlSessionFactory.openSession();
        AdminModel adminModel = null;
        try {
            AdminMapper mapper = session.getMapper(AdminMapper.class);
            adminModel = mapper.loginByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return adminModel;
    }
}
