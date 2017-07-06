package com.zhaopin.core.dao.impl;

import com.zhaopin.core.dao.ReportDao;
import com.zhaopin.core.dbutil.DBFactory;
import com.zhaopin.core.mapper.ReportMapper;
import com.zhaopin.core.model.ReportModel;
import com.zhaopin.core.model.ReportTimeModel;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhou.hao on 2017/7/5.
 */
@Repository("ReportDao")
public class ReportDaoImpl implements ReportDao {
    private SqlSessionFactory sqlSessionFactory = DBFactory.getSqlSessionFactory();

    @Override
    public int addReportData(ReportModel model) {
        SqlSession session = sqlSessionFactory.openSession();
        int number = 0;
        try {
            ReportMapper mapper = session.getMapper(ReportMapper.class);
            int count = mapper.count();
            int id = 0;
            if (count > 0) {
                id = mapper.getLastId();
            } else {
                id = 0;
            }
            model.setRid(id + 1);
            number = mapper.addReportData(model);
            if (number > 0) {
                session.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return number;
    }

    @Override
    public List<ReportModel> query(ReportTimeModel model) {
        SqlSession session = sqlSessionFactory.openSession();
        List<ReportModel> reportModels;
        try {
            ReportMapper mapper = session.getMapper(ReportMapper.class);
            reportModels = mapper.query(model);
        } catch (Exception e) {
            e.printStackTrace();
            reportModels = new ArrayList<>();
        } finally {
            session.close();
        }
        return reportModels;
    }
}
