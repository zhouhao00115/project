package com.zhaopin.core.mapper;

import com.zhaopin.core.dto.IndexTest;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zhou.hao on 2017/6/20.
 */
public interface IndexTestMapper {
    @Select("select * from example")
    List<IndexTest> query();
}
