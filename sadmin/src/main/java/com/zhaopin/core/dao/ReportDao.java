package com.zhaopin.core.dao;

import com.zhaopin.core.model.ReportModel;

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
}
