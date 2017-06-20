package com.zhaopin.core.dao.impl;

import com.zhaopin.core.dao.TestDao;
import com.zhaopin.core.dbutil.DBFactory;
import com.zhaopin.core.dto.IndexTest;
import com.zhaopin.core.mapper.CustomerMapper;
import com.zhaopin.core.mapper.IndexTestMapper;
import com.zhaopin.core.model.CustomerModel;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/6/17.
 */
@Repository("TestDao")
public class TestDapImpl implements TestDao {
    private SqlSessionFactory sqlSessionFactory = DBFactory.getSqlSessionFactory();

    @Override
    public List<IndexTest> test() {
        SqlSession session = sqlSessionFactory.openSession();
        List<IndexTest> models = null;
        try {
            IndexTestMapper mapper = session.getMapper(IndexTestMapper.class);
            models = mapper.query();
            for (IndexTest test : models) {
                System.out.println("id --" + test.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return models;
    }
}
