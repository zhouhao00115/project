package com.zhaopin.core.service.impl;

import com.zhaopin.core.dao.ReportDao;
import com.zhaopin.core.dto.report.ReportTableDto;
import com.zhaopin.core.model.ReportModel;
import com.zhaopin.core.model.ReportTimeModel;
import com.zhaopin.core.service.ReportService;
import com.zhaopin.core.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zhou.hao on 2017/7/6.
 */
@Service("ReportService")
public class ReportServiceImpl implements ReportService, Comparator {

    @Autowired
    private ReportDao reportDao;

    @Override
    public List<ReportTableDto> reportChart(Date startDate, Date endDate) {
        List<ReportTableDto> result = new ArrayList<>();
        //时间不合适
        long start = startDate.getTime();
        long end = endDate.getTime();
        if (start > end) {
            return result;
        }
        //同一个时间，默认返回一年内的消息
        ReportTimeModel model;
        if (start == end) {
            model = new ReportTimeModel(
                    DateUtil.convert2String(new Date(DateUtil.getTimestamp() - 365 * 24 * 60 * 60 * 1000), DateUtil.getFormatDate_02),
                    DateUtil.convert2String(new Date(DateUtil.getTimestamp()), DateUtil.getFormatDate_02));
        } else {
            model = new ReportTimeModel(
                    DateUtil.convert2String(startDate, DateUtil.getFormatDate_02),
                    DateUtil.convert2String(endDate, DateUtil.getFormatDate_02));
        }
        List<ReportModel> list = reportDao.query(model);
        Map<String, List<ReportModel>> map = new HashMap<>();
        for (ReportModel reportModel : list) {
            if (map.containsKey(reportModel.getCid())) {
                map.get(reportModel.getCid()).add(reportModel);
                continue;
            }
            List<ReportModel> info = new ArrayList<>();
            info.add(reportModel);
            map.put(reportModel.getCid(), info);
        }
        for (String key : map.keySet()) {
            ReportTableDto tableDto = new ReportTableDto();
            tableDto.setCid(key);
            tableDto.setModels(map.get(key));
            result.add(tableDto);
        }
        Collections.sort(result,ReportServiceImpl.this);
        return result;
    }

    @Override
    public int compare(Object o1, Object o2) {
        ReportTableDto dto1 = (ReportTableDto) o1;
        ReportTableDto dto2 = (ReportTableDto) o2;
        try {
            int a = Integer.parseInt(dto1.getCid().replace("HS", ""));
            int b = Integer.parseInt(dto2.getCid().replace("HS", ""));
            if (a < b) {
                return 1;
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
