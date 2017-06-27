package com.zhaopin.core.controller;

import com.zhaopin.core.dto.AdminDto;
import com.zhaopin.core.dto.admin.AdminView;
import com.zhaopin.core.model.AdminModel;
import com.zhaopin.core.service.AdminService;
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

/**
 * Created by zhou.hao on 2017/6/27.
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "admin.do", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request,
                              HttpServletResponse response,
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
            cookie.setPath("/admin.do");
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
        AdminDto dto = new AdminDto();
        dto.setList(adminService.getList(new AdminView(start, rows)));
        dto.setAdminCountDto(adminService.getCountAdmin());
        dto.setCount(dto.getAdminCountDto().getCount());
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
        mv.setViewName("admin");
        mv.addObject("view", 1);
        return mv;
    }

    @RequestMapping(value = "addadmin.do", method = RequestMethod.GET)
    public ModelAndView datauserinfo(@RequestParam(value = "id", defaultValue = "0") String id) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin");
        if (StringUtil.isNullOrEmpty(id)) {
            mv.addObject("dto", new AdminModel());
            mv.addObject("view", 3);
        } else {
            int number = 0;
            try{
                number = Integer.parseInt(id);
            }catch (Exception e){
                e.printStackTrace();
            }
            //标记返回的页面为列表页
            mv.addObject("dto", adminService.getAdminById(number));
        }
        return mv;
    }
}
