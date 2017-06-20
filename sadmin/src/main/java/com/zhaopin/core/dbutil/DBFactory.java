package com.zhaopin.core.dbutil;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/19.
 */
public class DBFactory {
    private static SqlSessionFactory sqlSessionFactory = null;

    public static SqlSessionFactory getSqlSessionFactory() {
        if (null == sqlSessionFactory) {
            synchronized (DBFactory.class) {
                if(null == sqlSessionFactory){
                    Reader reader = null;
                    try {
                        reader = Resources.getResourceAsReader("mybatis-config.xml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "sqlite");
                }
            }
        }
        return sqlSessionFactory;
    }
}
