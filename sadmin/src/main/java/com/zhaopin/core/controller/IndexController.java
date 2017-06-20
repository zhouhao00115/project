package com.zhaopin.core.controller;

import com.zhaopin.core.service.TestService;
import com.zhaopin.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/17.
 */
@Controller
public class IndexController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "login.do")
    public void login() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute("username");
    }

    @RequestMapping(value = "index.do", method = RequestMethod.GET)
    public String index() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (!StringUtil.idNullOrEmpty(username)) {
            return "index";
        }
        return "login";
    }

    @RequestMapping(value = "index.do", method = RequestMethod.POST)
    public String index(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        if ("admin".equals(username) && "admin".equals(password)) {
            session.setAttribute("username", username);
            return "index";
        }
        request.setAttribute("error", "用户名或密码输入错误！");
        return "login";
    }
}
