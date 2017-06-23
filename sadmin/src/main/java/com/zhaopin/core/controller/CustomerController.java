package com.zhaopin.core.controller;

import com.zhaopin.core.dto.CustomerDto;
import com.zhaopin.core.dto.customer.CustomerView;
import com.zhaopin.core.model.CustomerModel;
import com.zhaopin.core.service.CustomerService;
import com.zhaopin.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2017/6/18.
 */
@Controller
public class CustomerController {

    @Autowired
    private CustomerService service;

    @RequestMapping(value = "customer.do", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "start", defaultValue = "0") String numberstart,
                              @RequestParam(value = "rows", defaultValue = "10") String numberrows,
                              @CookieValue(value = "cookierows", defaultValue = "10") String cookierows) {
        int start;
        int rows;
        try {
            start = Integer.parseInt(numberstart);
            rows = Integer.parseInt(numberrows);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("输入的行数不为整数，报错");
            start = 0;
            rows = 10;
        }
        if (request.getParameterMap().containsKey("rows")) {
            Cookie cookie = new Cookie("cookierows", String.valueOf(rows));
            cookie.setPath("/customer.do");
            cookie.setMaxAge(30 * 60 * 60 * 15);// 设置为半个月
            response.addCookie(cookie);
        } else {
            try {
                rows = Integer.parseInt(cookierows);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("customer");
        List<CustomerModel> list = service.getList(new CustomerView(start, rows));
        CustomerDto dto = new CustomerDto();
        dto.setCount(list.size());
        dto.setList(list);
        dto.setPage(1);
        mv.addObject("dto", dto);
        //标记返回的页面为列表页
        mv.addObject("view", 1);
        return mv;
    }

    @RequestMapping(value = "customerinfo.do", method = RequestMethod.GET)
    public ModelAndView customerinfo(@RequestParam(value = "number", defaultValue = "") String number) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("customer");
        //标记返回的页面为列表页
        mv.addObject("view", 2);
        CustomerModel model = service.getModelById(new CustomerView(number));
        mv.addObject("dto",model);
        return mv;
    }

}
