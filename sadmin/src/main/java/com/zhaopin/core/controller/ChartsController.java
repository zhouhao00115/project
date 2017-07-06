package com.zhaopin.core.controller;

import com.google.gson.Gson;
import com.zhaopin.core.dto.report.ReportTableDto;
import com.zhaopin.core.model.ReportModel;
import com.zhaopin.core.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by zhou.hao on 2017/7/4.
 */
@Controller
public class ChartsController {

    @Autowired
    private ReportService reportService;

    @RequestMapping(value = "charts.do", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("chart");
        List<ReportTableDto> list = reportService.reportChart(new Date());
        System.out.println(new Gson().toJson(list));
        mv.addObject("dto",list);
        mv.addObject("key",1);
        return mv;
    }
}
