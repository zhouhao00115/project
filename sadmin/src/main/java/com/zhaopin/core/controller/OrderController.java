package com.zhaopin.core.controller;

import com.zhaopin.core.dto.OrderAddInfoDto;
import com.zhaopin.core.dto.OrdersDto;
import com.zhaopin.core.dto.Track.TrackView;
import com.zhaopin.core.dto.customer.CustomerView;
import com.zhaopin.core.dto.datauser.DataUserView;
import com.zhaopin.core.dto.order.OrderView;
import com.zhaopin.core.model.CustomerModel;
import com.zhaopin.core.model.DataUserModel;
import com.zhaopin.core.model.OrderModel;
import com.zhaopin.core.model.TrackModel;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
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

    @RequestMapping(value = "orderinfo.do", method = RequestMethod.GET)
    public ModelAndView orderinfo(@RequestParam(value = "oid", defaultValue = "") String oid) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("order");
        mv.addObject("view", 2);
        mv.addObject("dto",service.getOrderById(oid));
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
            orderAddInfoDto.setOrderModel(service.getOrderById(oid));
        }
        mv.addObject("info", "");
        mv.addObject("dto", orderAddInfoDto);
        return mv;
    }

    @RequestMapping(value = "addorder.do", method = RequestMethod.POST)
    public ModelAndView datauseraddaction(@RequestParam(value = "oid", defaultValue = "") String oid,
                                          @RequestParam(value = "cid", defaultValue = "") String cid,
                                          @RequestParam(value = "tid", defaultValue = "") String tid,
                                          @RequestParam(value = "volume", defaultValue = "") String volume,
                                          @RequestParam(value = "price", defaultValue = "") String price,
                                          @RequestParam(value = "total", defaultValue = "") String total,
                                          @RequestParam(value = "staffid", defaultValue = "") String staffid,
                                          @RequestParam(value = "remarks", defaultValue = "") String remarks) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String power = (String) session.getAttribute("power");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("order");
        mv.addObject("view", 3);
        OrderAddInfoDto orderAddInfoDto = new OrderAddInfoDto();
        if (!"1".equals(power)) {
            orderAddInfoDto.setCustomers(customerService.getList(new CustomerView(0, 10000)));
            orderAddInfoDto.setTracks(trackService.query(new TrackView(0, 10000)));
            orderAddInfoDto.setDataUser(dataUserService.query(new DataUserView(0, 10000)));
            mv.addObject("info", "对不起，您的权限不够,请联系管理员");
            if (StringUtil.isNullOrEmpty(oid)) {
                orderAddInfoDto.setOrderModel(new OrderModel());
            } else {
                orderAddInfoDto.setOrderModel(service.getOrderById(oid));
            }
            mv.addObject("dto", orderAddInfoDto);
            return mv;
        }
        if (StringUtil.isNullOrEmpty(cid)) {
            orderAddInfoDto.setCustomers(customerService.getList(new CustomerView(0, 10000)));
            orderAddInfoDto.setTracks(trackService.query(new TrackView(0, 10000)));
            orderAddInfoDto.setDataUser(dataUserService.query(new DataUserView(0, 10000)));
            mv.addObject("info", "对不起，牧场信息不能为空");
            if (StringUtil.isNullOrEmpty(oid)) {
                orderAddInfoDto.setOrderModel(new OrderModel());
            } else {
                orderAddInfoDto.setOrderModel(service.getOrderById(oid));
            }
            mv.addObject("dto", orderAddInfoDto);
            return mv;
        }
        try {
            Integer.parseInt(tid);
        } catch (Exception e) {
            e.printStackTrace();
            orderAddInfoDto.setCustomers(customerService.getList(new CustomerView(0, 10000)));
            orderAddInfoDto.setTracks(trackService.query(new TrackView(0, 10000)));
            orderAddInfoDto.setDataUser(dataUserService.query(new DataUserView(0, 10000)));
            mv.addObject("info", "对不起，货车信息不能为空");
            if (StringUtil.isNullOrEmpty(oid)) {
                orderAddInfoDto.setOrderModel(new OrderModel());
            } else {
                orderAddInfoDto.setOrderModel(service.getOrderById(oid));
            }
            mv.addObject("dto", orderAddInfoDto);
            return mv;
        }
        int volumeNumber;
        try {
            volumeNumber = Integer.parseInt(volume);
        } catch (Exception e) {
            e.printStackTrace();
            orderAddInfoDto.setCustomers(customerService.getList(new CustomerView(0, 10000)));
            orderAddInfoDto.setTracks(trackService.query(new TrackView(0, 10000)));
            orderAddInfoDto.setDataUser(dataUserService.query(new DataUserView(0, 10000)));
            mv.addObject("info", "对不起，发货量应该为整数");
            if (StringUtil.isNullOrEmpty(oid)) {
                orderAddInfoDto.setOrderModel(new OrderModel());
            } else {
                orderAddInfoDto.setOrderModel(service.getOrderById(oid));
            }
            mv.addObject("dto", orderAddInfoDto);
            return mv;
        }
        double priceDouble;
        try {
            priceDouble = Double.parseDouble(price);
        } catch (Exception e) {
            e.printStackTrace();
            orderAddInfoDto.setCustomers(customerService.getList(new CustomerView(0, 10000)));
            orderAddInfoDto.setTracks(trackService.query(new TrackView(0, 10000)));
            orderAddInfoDto.setDataUser(dataUserService.query(new DataUserView(0, 10000)));
            mv.addObject("info", "对不起，单价应为小数类型");
            if (StringUtil.isNullOrEmpty(oid)) {
                orderAddInfoDto.setOrderModel(new OrderModel());
            } else {
                orderAddInfoDto.setOrderModel(service.getOrderById(oid));
            }
            mv.addObject("dto", orderAddInfoDto);
            return mv;
        }
        int totalNumber;
        try {
            totalNumber = Integer.parseInt(total);
        } catch (Exception e) {
            e.printStackTrace();
            orderAddInfoDto.setCustomers(customerService.getList(new CustomerView(0, 10000)));
            orderAddInfoDto.setTracks(trackService.query(new TrackView(0, 10000)));
            orderAddInfoDto.setDataUser(dataUserService.query(new DataUserView(0, 10000)));
            mv.addObject("info", "对不起，总价应为整数");
            if (StringUtil.isNullOrEmpty(oid)) {
                orderAddInfoDto.setOrderModel(new OrderModel());
            } else {
                orderAddInfoDto.setOrderModel(service.getOrderById(oid));
            }
            mv.addObject("dto", orderAddInfoDto);
            return mv;
        }
        try {
            Integer.parseInt(staffid);
        } catch (Exception e) {
            e.printStackTrace();
            orderAddInfoDto.setCustomers(customerService.getList(new CustomerView(0, 10000)));
            orderAddInfoDto.setTracks(trackService.query(new TrackView(0, 10000)));
            orderAddInfoDto.setDataUser(dataUserService.query(new DataUserView(0, 10000)));
            mv.addObject("info", "对不起，发货员不能为空");
            if (StringUtil.isNullOrEmpty(oid)) {
                orderAddInfoDto.setOrderModel(new OrderModel());
            } else {
                orderAddInfoDto.setOrderModel(service.getOrderById(oid));
            }
            mv.addObject("dto", orderAddInfoDto);
            return mv;
        }
        CustomerModel customerModel = customerService.getModelById(new CustomerView(cid));
        if (StringUtil.isNullOrEmpty(customerModel.getCid())) {
            mv.addObject("info", "对不起，牧场信息不存在");
            orderAddInfoDto.setCustomers(customerService.getList(new CustomerView(0, 10000)));
            orderAddInfoDto.setTracks(trackService.query(new TrackView(0, 10000)));
            orderAddInfoDto.setDataUser(dataUserService.query(new DataUserView(0, 10000)));
            if (StringUtil.isNullOrEmpty(oid)) {
                orderAddInfoDto.setOrderModel(new OrderModel());
            } else {
                orderAddInfoDto.setOrderModel(service.getOrderById(oid));
            }
            mv.addObject("dto", orderAddInfoDto);
            return mv;
        }
        TrackModel trackModel = trackService.getTrackById(tid);
        if (trackModel.getTid() == 0) {
            orderAddInfoDto.setCustomers(customerService.getList(new CustomerView(0, 10000)));
            orderAddInfoDto.setTracks(trackService.query(new TrackView(0, 10000)));
            orderAddInfoDto.setDataUser(dataUserService.query(new DataUserView(0, 10000)));
            mv.addObject("info", "对不起，货车信息不存在");
            if (StringUtil.isNullOrEmpty(oid)) {
                orderAddInfoDto.setOrderModel(new OrderModel());
            } else {
                orderAddInfoDto.setOrderModel(service.getOrderById(oid));
            }
            mv.addObject("dto", orderAddInfoDto);
            return mv;
        }
        DataUserModel dataUserModel = dataUserService.getDataUserById(staffid);
        if (dataUserModel.getStaffid() == 0) {
            orderAddInfoDto.setCustomers(customerService.getList(new CustomerView(0, 10000)));
            orderAddInfoDto.setTracks(trackService.query(new TrackView(0, 10000)));
            orderAddInfoDto.setDataUser(dataUserService.query(new DataUserView(0, 10000)));
            mv.addObject("info", "对不起，数据员信息不存在");
            if (StringUtil.isNullOrEmpty(oid)) {
                orderAddInfoDto.setOrderModel(new OrderModel());
            } else {
                orderAddInfoDto.setOrderModel(service.getOrderById(oid));
            }
            mv.addObject("dto", orderAddInfoDto);
            return mv;
        }
        OrderModel model = new OrderModel();
        model.setCustomerModel(customerModel);
        model.setDataUserModel(dataUserModel);
        model.setTrackModel(trackModel);
        model.setPrice(priceDouble);
        model.setTotal(totalNumber);
        model.setVolume(volumeNumber);
        model.setRemarks(remarks);
        //表示添加
        if (StringUtil.isNullOrEmpty(oid)||"0".equals(oid)) {
            orderAddInfoDto.setCustomers(customerService.getList(new CustomerView(0, 10000)));
            orderAddInfoDto.setTracks(trackService.query(new TrackView(0, 10000)));
            orderAddInfoDto.setDataUser(dataUserService.query(new DataUserView(0, 10000)));
            model.setOid(0);
            OrderModel orderModel = service.addOrder(model);
            if(orderModel.getOid()>0){
                mv.addObject("info", "恭喜，添加成功");
            }else {
                mv.addObject("info", "对不起，添加失败");
            }
            orderAddInfoDto.setOrderModel(orderModel);
            mv.addObject("dto", orderAddInfoDto);
            return mv;
        }
        int oidNumber;
        try{
            oidNumber = Integer.parseInt(oid);
        }catch (Exception e){
            e.printStackTrace();
            orderAddInfoDto.setCustomers(customerService.getList(new CustomerView(0, 10000)));
            orderAddInfoDto.setTracks(trackService.query(new TrackView(0, 10000)));
            orderAddInfoDto.setDataUser(dataUserService.query(new DataUserView(0, 10000)));
            mv.addObject("info", "对不起，修改的订单数据有问题");
            if (StringUtil.isNullOrEmpty(oid)) {
                orderAddInfoDto.setOrderModel(new OrderModel());
            } else {
                orderAddInfoDto.setOrderModel(service.getOrderById(oid));
            }
            mv.addObject("dto", orderAddInfoDto);
            return mv;
        }
        //修改完毕，跳到详情页
        model.setOid(oidNumber);
        mv.addObject("view", 2);
        OrderModel orderModel = service.addOrder(model);
        mv.addObject("dto",orderModel);
        return mv;
    }
}
