package com.zhaopin.core.controller;

import com.zhaopin.core.dto.CustomerDto;
import com.zhaopin.core.dto.customer.CustomerView;
import com.zhaopin.core.model.CustomerModel;
import com.zhaopin.core.service.CustomerService;
import com.zhaopin.core.service.DataUserService;
import com.zhaopin.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2017/6/18.
 */
@Controller
public class CustomerController {

    @Autowired
    private CustomerService service;
    @Autowired
    private DataUserService dataUserService;

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
        dto.setCount(service.getCountCustomer());
        dto.setList(list);
        dto.setPage(start/rows+1);
        dto.setStart(start);
        dto.setRows(rows);
        if (dto.getCount() > rows) {
            if(dto.getCount() % rows == 0){
                dto.setEnd((dto.getCount() / rows) * rows-rows);
            }else {
                dto.setEnd((dto.getCount() / rows) * rows);
            }
        }
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
        mv.addObject("dto", model);
        return mv;
    }

    @RequestMapping(value = "customeradd.do", method = RequestMethod.GET)
    public ModelAndView customeradd(@RequestParam(value = "cid", defaultValue = "") String cid) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("customer");
        //标记返回的页面为列表页
        mv.addObject("view", 3);
        mv.addObject("datauser", dataUserService.allUser());
        if (StringUtil.isNullOrEmpty(cid)) {
            mv.addObject("dto", new CustomerModel());
            return mv;
        }
        CustomerModel model = service.getModelById(new CustomerView(cid));
        mv.addObject("dto", model);
        return mv;
    }

    @RequestMapping(value = "addcustomer.do", method = RequestMethod.POST)
    public ModelAndView customeraddaction(@RequestParam(value = "cid", defaultValue = "") String cid,
                                          @RequestParam(value = "name", defaultValue = "") String name,
                                          @RequestParam(value = "city", defaultValue = "") String city,
                                          @RequestParam(value = "address", defaultValue = "") String address,
                                          @RequestParam(value = "cname", defaultValue = "") String cname,
                                          @RequestParam(value = "cphone", defaultValue = "") String cphone,
                                          @RequestParam(value = "naicity", defaultValue = "") String naicity,
                                          @RequestParam(value = "naiaddress", defaultValue = "") String naiaddress,
                                          @RequestParam(value = "scale", defaultValue = "") String scale,
                                          @RequestParam(value = "used", defaultValue = "") String used,
                                          @RequestParam(value = "price", defaultValue = "") String price,
                                          @RequestParam(value = "left", defaultValue = "") String left,
                                          @RequestParam(value = "road", defaultValue = "") String road,
                                          @RequestParam(value = "longitude", defaultValue = "") String longitude,
                                          @RequestParam(value = "latitude", defaultValue = "") String latitude,
                                          @RequestParam(value = "remarks", defaultValue = "") String remarks,
                                          @RequestParam(value = "staffid", defaultValue = "") String staffid) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String power = (String) session.getAttribute("power");
        ModelAndView mv = new ModelAndView();
        if (Integer.parseInt(power) > 2) {
            mv.setViewName("index");
            return mv;
        }
        CustomerModel model = new CustomerModel();
        model.setName(name);
        model.setCity(city);
        model.setAddress(address);
        model.setCname(cname);
        model.setCphone(cphone);
        model.setNaicity(naicity);
        model.setNaiaddress(naiaddress);
        model.setScale(scale);
        model.setLongitude(Double.parseDouble(longitude));
        model.setLatitude(Double.parseDouble(latitude));
        try {
            if (StringUtil.isNullOrEmpty(used)) {
                used = "0";
            }
            model.setUsed(Integer.parseInt(used));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (StringUtil.isNullOrEmpty(left)) {
                left = "0";
            }
            model.setLeft(Integer.parseInt(left));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (StringUtil.isNullOrEmpty(price)) {
                price = "0";
            }
            model.setPrice(Integer.parseInt(price));
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.setRoad(road);
        model.setRemarks(remarks);
        try {
            if (StringUtil.isNullOrEmpty(staffid)) {
                staffid = "1";
            }
            model.setStaffid(Integer.parseInt(staffid));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //隐藏域的值不为空表示是修改
        CustomerModel dtoModel;
        if (!StringUtil.isNullOrEmpty(cid)) {
            model.setCid(cid);
            dtoModel = service.updateCustomer(model);
        } else {
            dtoModel = service.addCustomer(model);
        }
        mv.setViewName("customer");
        //标记返回的页面为列表页
        mv.addObject("view", 2);
        mv.addObject("dto", dtoModel);
        return mv;
    }

    @RequestMapping(value = "deletecustomer.do", method = RequestMethod.GET)
    public
    @ResponseBody
    int customerdeleteaction(@RequestParam(value = "cid", defaultValue = "") String cid) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String power = (String) session.getAttribute("power");
        //-1  表示没权限  0 表示失败  1 表示成功
        if (Integer.parseInt(power) > 2) {
            return -1;
        }
        return service.deleteCustomerById(cid);
    }
}
