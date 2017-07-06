package com.zhaopin.core.controller;

import com.google.gson.Gson;
import com.zhaopin.core.dto.report.ReportTableDto;
import com.zhaopin.core.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by zhou.hao on 2017/7/6.
 */
@Controller
public class ReportController {
    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "reporttest.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public
    @ResponseBody
    String customerdeleteaction() {
        List<ReportTableDto> dto = reportService.reportChart(new Date());
        return new Gson().toJson(dto);
    }
}
