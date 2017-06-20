package com.zhaopin.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2017/6/18.
 */
@Controller
public class MapController {
    @RequestMapping(value = "map.do", method = RequestMethod.GET)
    public ModelAndView index() {
        System.out.printf("mapcontroller index");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("customer/mapinfo");
        return mv;
    }
}
