package com.zhaopin.core.service;

import com.zhaopin.core.dto.report.ReportTableDto;

import java.util.Date;
import java.util.List;

/**
 * Created by zhou.hao on 2017/7/6.
 */
public interface ReportService {
    public List<ReportTableDto> reportChart(Date startDate , Date endDate);
    public List<ReportTableDto> reportChart(Date startDate);
}
