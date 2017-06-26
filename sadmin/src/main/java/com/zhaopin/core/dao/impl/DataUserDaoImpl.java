package com.zhaopin.core.dao.impl;

import com.zhaopin.core.dao.DataUserDao;
import com.zhaopin.core.dbutil.DBFactory;
import com.zhaopin.core.dto.datauser.DataUserView;
import com.zhaopin.core.mapper.DataUserMapper;
import com.zhaopin.core.model.DataUserModel;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import javax.mail.Session;
import java.util.List;

/**
 * Created by Administrator on 2017/6/25.
 */
@Repository("DataUserDao")
public class DataUserDaoImpl implements DataUserDao {
    private SqlSessionFactory sqlSessionFactory = DBFactory.getSqlSessionFactory();

    @Override
    public List<DataUserModel> query(DataUserView view) {
        SqlSession session = sqlSessionFactory.openSession();
        List<DataUserModel> dataUserModels = null;
        try {
            DataUserMapper mapper = session.getMapper(DataUserMapper.class);
            dataUserModels = mapper.query(view);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return dataUserModels;
    }

    @Override
    public List<DataUserModel> allUser() {
        SqlSession session = sqlSessionFactory.openSession();
        List<DataUserModel> dataUserModels = null;
        try {
            DataUserMapper mapper = session.getMapper(DataUserMapper.class);
            dataUserModels = mapper.allUser();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return dataUserModels;
    }

    @Override
    public int count() {
        SqlSession session = sqlSessionFactory.openSession();
        int number = 0;
        try {
            DataUserMapper mapper = session.getMapper(DataUserMapper.class);
            number = mapper.count();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return number;
    }

    @Override
    public DataUserModel getDataUserById(int staffid) {
        SqlSession session = sqlSessionFactory.openSession();
        DataUserModel model = null;
        try {
            DataUserMapper mapper = session.getMapper(DataUserMapper.class);
            model = mapper.getUserById(staffid);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return model;
    }

    @Override
    public DataUserModel addDataUser(DataUserModel model) {
        SqlSession session = sqlSessionFactory.openSession();
        DataUserModel returnModel = null;
        try {
            DataUserMapper mapper = session.getMapper(DataUserMapper.class);
            int id = mapper.getLastId();
            id += 1;
            model.setStaffid(id);
            int rows = mapper.addDataUser(model);
            if (rows > 0) {
                returnModel = mapper.getUserById(id);
                session.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return returnModel;
    }

    @Override
    public DataUserModel updateDataUser(DataUserModel model) {
        SqlSession session = sqlSessionFactory.openSession();
        DataUserModel returnModel = null;
        try {
            DataUserMapper mapper = session.getMapper(DataUserMapper.class);
            int rows = mapper.updateDataUser(model);
            if (rows > 0) {
                session.commit();
                return model;
            }
            returnModel = mapper.getUserById(model.getStaffid());
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return returnModel;
    }
}
