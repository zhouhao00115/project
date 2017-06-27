package com.zhaopin.core.controller;

import com.zhaopin.core.dto.DataUserDto;
import com.zhaopin.core.dto.customer.CustomerView;
import com.zhaopin.core.dto.datauser.DataUserView;
import com.zhaopin.core.model.CustomerModel;
import com.zhaopin.core.model.DataUserModel;
import com.zhaopin.core.service.DataUserService;
import com.zhaopin.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2017/6/25.
 */
@Controller
public class DataUserController {
    @Autowired
    private DataUserService dataUserService;

    @RequestMapping(value = "datauser.do", method = RequestMethod.GET)
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
            cookie.setPath("/datauser.do");
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
        mv.setViewName("datauser");
        List<DataUserModel> models = dataUserService.query(new DataUserView(start, rows));
        DataUserDto dataUserDto = new DataUserDto();
        dataUserDto.setList(models);
        dataUserDto.setPage(start/rows+1);
        dataUserDto.setStart(start);
        dataUserDto.setCount(dataUserService.count());
        dataUserDto.setRows(rows);
        if (dataUserDto.getCount() > rows) {
            if(dataUserDto.getCount() % rows == 0){
                dataUserDto.setEnd((dataUserDto.getCount() / rows) * rows-rows);
            }else {
                dataUserDto.setEnd((dataUserDto.getCount() / rows) * rows);
            }
        }
        dataUserDto.setCount(dataUserService.count());
        mv.addObject("dto", dataUserDto);
        mv.addObject("view", 1);
        return mv;
    }

    @RequestMapping(value = "adddatauseraction.do", method = RequestMethod.POST)
    public ModelAndView addaction(@RequestParam(value = "staffid", defaultValue = "") String staffid,
                                  @RequestParam(value = "sname", defaultValue = "") String sname,
                                  @RequestParam(value = "sphone", defaultValue = "") String sphone,
                                  @RequestParam(value = "gender", defaultValue = "0") String gender,
                                  @RequestParam(value = "remarks", defaultValue = "") String remarks) {
        DataUserModel model = new DataUserModel();
        model.setSname(sname);
        model.setSphone(sphone);
        try {
            model.setGender(Integer.parseInt(gender));
        } catch (Exception e) {
            e.printStackTrace();
            model.setGender(1);
        }
        model.setRemarks(remarks);
        if (!(StringUtil.isNullOrEmpty(staffid) || "0".equals(staffid))) {
            //修改数据员
            try {
                model.setStaffid(Integer.parseInt(staffid));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("datauser");
        mv.addObject("dto", dataUserService.addUser(model));
        mv.addObject("view", 2);
        return mv;
    }

    @RequestMapping(value = "datauserinfo.do", method = RequestMethod.GET)
    public ModelAndView datauserinfo(@RequestParam(value = "staffid", defaultValue = "") String staffid) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("datauser");
        mv.addObject("view", 2);
        if (StringUtil.isNullOrEmpty(staffid)) {
            mv.addObject("dto", new DataUserModel());
        } else {
            //标记返回的页面为列表页
            mv.addObject("dto", dataUserService.getDataUserById(staffid));
        }
        return mv;
    }

    @RequestMapping(value = "adddatauser.do", method = RequestMethod.GET)
    public ModelAndView datauseradd(@RequestParam(value = "staffid", defaultValue = "") String staffid) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("datauser");
        mv.addObject("view", 3);
        if (StringUtil.isNullOrEmpty(staffid)) {
            mv.addObject("dto", new DataUserModel());
        } else {
            //标记返回的页面为列表页
            mv.addObject("dto", dataUserService.getDataUserById(staffid));
        }
        return mv;
    }
}
