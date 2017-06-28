package com.zhaopin.core.controller;

import com.zhaopin.core.dto.customer.CustomerView;
import com.zhaopin.core.model.AdminModel;
import com.zhaopin.core.service.AdminService;
import com.zhaopin.core.service.CustomerService;
import com.zhaopin.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/17.
 */
@Controller
public class IndexController {
    @Autowired
    private AdminService service;
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "login.do")
    public void login() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute("username");
    }

    @RequestMapping(value = "index.do", method = RequestMethod.GET)
    public ModelAndView index() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        ModelAndView mv = new ModelAndView();
        String username = (String) session.getAttribute("username");
        if (!StringUtil.isNullOrEmpty(username)) {
            mv.addObject("list",customerService.getList(new CustomerView(0,500)));
            mv.setViewName("index");
            return mv;
        }
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping(value = "index.do", method = RequestMethod.POST)
    public ModelAndView index(@RequestParam(value = "username") String username,
                              @RequestParam(value = "password") String password) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        ModelAndView mv = new ModelAndView();
        //验证登陆信息
        if (StringUtil.isNullOrEmpty(username) || StringUtil.isNullOrEmpty(password)) {
            request.setAttribute("error", "用户名或密码不能为空！");
            mv.setViewName("login");
            return mv;
        }
        AdminModel adminModel = service.loginByName(username);
        if (null == adminModel) {
            request.setAttribute("error", "用户名不存在！");
            mv.setViewName("login");
            return mv;
        }
        if (adminModel.getUsername().equals(username) && adminModel.getPassword().equals(password)) {
            session.setAttribute("id", adminModel.getId());
            session.setAttribute("username", username);
            session.setAttribute("power", String.valueOf(adminModel.getPower()));
            mv.addObject("list",customerService.getList(new CustomerView(0,500)));
            mv.setViewName("index");
            return mv;
        }
        request.setAttribute("error", "用户名或密码输入错误！");
        mv.setViewName("login");
        return mv;
    }
}
