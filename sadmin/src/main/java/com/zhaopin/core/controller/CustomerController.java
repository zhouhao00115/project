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
                              @RequestParam(value = "rows", defaultValue = "10") String rows,
                              @CookieValue(value = "cookierows", defaultValue = "10") String cookierows) {
        int number;
        try {
            number = Integer.parseInt(rows);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("输入的行数不为整数，报错");
            number = 10;
        }
        if (request.getParameterMap().containsKey("rows")) {
            Cookie cookie = new Cookie("cookierows", String.valueOf(number));
            cookie.setPath("/customer.do");
            cookie.setMaxAge(30 * 60 * 60 * 15);// 设置为半个月
            response.addCookie(cookie);
        } else {
            try {
                number = Integer.parseInt(cookierows);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("customer");
        List<CustomerModel> list = service.getList(new CustomerView(0, number));
        CustomerDto dto = new CustomerDto();
        dto.setCount(list.size());
        dto.setList(list);
        dto.setPage(1);
        mv.addObject("dto", dto);
        return mv;
    }

    @RequestMapping(value = "customerinfo.do", method = RequestMethod.GET)
    public ModelAndView customerinfo(@RequestParam(value = "number", defaultValue = "") String number) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("customer");
        mv.addObject("number", 2);
        if (StringUtil.idNullOrEmpty(number)) {
            return mv;
        }
        CustomerModel model = new CustomerModel();
//        model.setId(number);
//        model.setCity("衡水市");
//        model.setName("张三牧场");
//        model.setPhone("13500000001");
//        model.setDataUser("李四");
//        model.setDataUserPhone("1360001111");
//        Random random = new Random();
//        model.setScale(random.nextInt(4));
//        model.setAddress("衡水市XX市XX乡XX村");
//        model.setUserName("张三");
//        model.setSendAddress("衡水市XX市XX乡XX村");
//        model.setUsed(10);
//        model.setSave("");
//        model.setSurplus(130);
//        model.setRoad("全程高速");
//        model.setFreight(30);
//        model.setPriceFreight(0);
//        model.setRemark("备注");
//        mv.addObject("dto", model);
        return mv;
    }

}
