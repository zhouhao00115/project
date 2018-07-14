package com.zhaopin.core.dao.impl;

import com.zhaopin.core.dao.RamanDao;
import com.zhaopin.core.dbutil.DBFactory;
import com.zhaopin.core.mapper.MysqlMapper;
import com.zhaopin.core.model.RamanModel;
import com.zhaopin.core.model.RowsModel;
import com.zhaopin.core.model.msqlModel.BaseData;
import com.zhaopin.core.model.msqlModel.RamanUpdate;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class RamanDaoImpl implements RamanDao {
    private SqlSessionFactory sqlSessionFactory = DBFactory.getMysqlSqlSessionFactory();

    @Override
    public List<RamanModel> allRaman(RowsModel rowsModel) {
        SqlSession session = sqlSessionFactory.openSession();
        List<RamanModel> ramanModels = null;
        try {
            MysqlMapper mapper = session.getMapper(MysqlMapper.class);
            ramanModels = mapper.allRaman(rowsModel);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ramanModels;
    }

    @Override
    public List<BaseData> getBaseData(RowsModel rowsModel) {
        SqlSession session = sqlSessionFactory.openSession();
        List<BaseData> baseDataList = null;
        try {
            MysqlMapper mapper = session.getMapper(MysqlMapper.class);
            baseDataList = mapper.getBaseData(rowsModel);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return baseDataList;
    }

    @Override
    public int updateOrders(RamanUpdate model) {
        SqlSession session = sqlSessionFactory.openSession();
        int number = 0;
        try {
            MysqlMapper mapper = session.getMapper(MysqlMapper.class);
            number = mapper.updateRamanData(model);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return number;
    }
}
