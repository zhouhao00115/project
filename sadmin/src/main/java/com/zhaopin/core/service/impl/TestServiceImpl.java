package com.zhaopin.core.service.impl;

import com.zhaopin.core.dao.TestDao;
import com.zhaopin.core.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/6/17.
 */
@Service("TestService")
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao dao;

    @Override
    public void test() {
        System.out.println("service");
        dao.test();
    }
}
