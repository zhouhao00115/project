package com.zhaopin.core.controller;

import com.zhaopin.core.dto.OrderAddInfoDto;
import com.zhaopin.core.dto.OrdersDto;
import com.zhaopin.core.dto.Track.TrackView;
import com.zhaopin.core.dto.customer.CustomerView;
import com.zhaopin.core.dto.datauser.DataUserView;
import com.zhaopin.core.dto.order.OrderView;
import com.zhaopin.core.model.OrderModel;
import com.zhaopin.core.service.CustomerService;
import com.zhaopin.core.service.DataUserService;
import com.zhaopin.core.service.OrderService;
import com.zhaopin.core.service.TrackService;
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
 * Created by zhou.hao on 2017/6/30.
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TrackService trackService;

    @Autowired
    private DataUserService dataUserService;

    @RequestMapping(value = "order.do", method = RequestMethod.GET)
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
            cookie.setPath("/order.do");
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
        mv.setViewName("order");
        List<OrderModel> list = service.query(new OrderView(start, rows));
        OrdersDto dto = new OrdersDto();
        dto.setCount(service.count());
        dto.setList(list);
        dto.setPage(start / rows + 1);
        dto.setStart(start);
        dto.setRows(rows);
        if (dto.getCount() > rows) {
            if (dto.getCount() % rows == 0) {
                dto.setEnd((dto.getCount() / rows) * rows - rows);
            } else {
                dto.setEnd((dto.getCount() / rows) * rows);
            }
        }
        mv.addObject("dto", dto);
        //标记返回的页面为列表页
        mv.addObject("view", 1);
        return mv;
    }

    @RequestMapping(value = "addorder.do", method = RequestMethod.GET)
    public ModelAndView datauseradd(@RequestParam(value = "oid", defaultValue = "") String oid) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("order");
        mv.addObject("view", 3);
        OrderAddInfoDto orderAddInfoDto = new OrderAddInfoDto();
        orderAddInfoDto.setCustomers(customerService.getList(new CustomerView(0, 10000)));
        orderAddInfoDto.setTracks(trackService.query(new TrackView(0, 10000)));
        orderAddInfoDto.setDataUser(dataUserService.query(new DataUserView(0, 10000)));
        if (StringUtil.isNullOrEmpty(oid)) {
            orderAddInfoDto.setOrderModel(new OrderModel());
        } else {
            orderAddInfoDto.setOrderModel(service.getDataUserById(oid));
        }
        return mv;
    }
}
