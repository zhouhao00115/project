package com.zhaopin.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhou.hao on 2017/7/4.
 */
@Controller
public class ChartsController {
    @RequestMapping(value = "charts.do", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("chart");
        return mv;
    }
}
