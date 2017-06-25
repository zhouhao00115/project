package com.zhaopin.core.controller;

import com.zhaopin.core.dto.DataUserDto;
import com.zhaopin.core.dto.datauser.DataUserView;
import com.zhaopin.core.model.DataUserModel;
import com.zhaopin.core.service.DataUserService;
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
@RequestMapping("datauser")
public class DataUserController {
    @Autowired
    private DataUserService dataUserService;

    @RequestMapping(method = RequestMethod.GET)
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
        List<DataUserModel> models = dataUserService.query(new DataUserView(start,rows));
        DataUserDto dataUserDto = new DataUserDto();
        dataUserDto.setList(models);
        dataUserDto.setPage(1);
        dataUserDto.setCount(dataUserService.count());
        mv.addObject("dto",dataUserDto);
        mv.addObject("view", 1);
        return mv;
    }
}
