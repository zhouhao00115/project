package com.zhaopin.core.dao.impl;

import com.zhaopin.core.dao.ReportDao;
import com.zhaopin.core.dbutil.DBFactory;
import com.zhaopin.core.mapper.ReportMapper;
import com.zhaopin.core.model.ReportModel;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

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
}
