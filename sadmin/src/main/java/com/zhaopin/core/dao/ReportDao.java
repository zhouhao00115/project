package com.zhaopin.core.dao;

import com.zhaopin.core.model.ReportModel;
import com.zhaopin.core.model.ReportTimeModel;

import java.util.List;

/**
 * Created by zhou.hao on 2017/7/4.
 */
public interface ReportDao {
    /**
     * 汇总每天查询到的数量，存入到库中
     *
     * @return
     */
    public int addReportData(ReportModel model);

    /**
     * 查询获取某天到昨天之间的数据汇总 day的格式为 2017-07-04
     *
     * @return
     */
    public List<ReportModel> query(ReportTimeModel model);
}
