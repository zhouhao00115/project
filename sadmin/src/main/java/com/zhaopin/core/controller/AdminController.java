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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        HttpSession session = request.getSession();
        ModelAndView mv = new ModelAndView();
        if (!"admin".equals(session.getAttribute("username"))) {
            mv.setViewName("admin");
            mv.addObject("add", 3);
            mv.addObject("view", 3);
            mv.addObject("info", "");
            //标记返回的页面为列表页
            mv.addObject("dto", adminService.getAdminById((int) session.getAttribute("id")));
            return mv;
        }
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
    public ModelAndView datauserinfo(@RequestParam(value = "id", defaultValue = "") String id) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        ModelAndView mv = new ModelAndView();
//        add = 1 表示添加 2 表示修改权限  3 表示修改密码
        mv.setViewName("admin");
        mv.addObject("view", 3);
        mv.addObject("info", "");
        if (StringUtil.isNullOrEmpty(id) && "admin".equals(session.getAttribute("username"))) {
            //管理员添加
            mv.addObject("dto", new AdminModel());
            mv.addObject("add", 1);
            return mv;
        }
        if ("admin".equals(session.getAttribute("username"))) {
            //管理员修改权限功能
            int number = 0;
            try {
                number = Integer.parseInt(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //标记返回的页面为列表页
            mv.addObject("add", 2);
            mv.addObject("dto", adminService.getAdminById(number));
            return mv;
        }
        mv.addObject("add", 3);
        //标记返回的页面为列表页
        mv.addObject("dto", adminService.getAdminById((int) session.getAttribute("id")));
        return mv;
    }

    /**
     * 添加用户名和密码或者修改个人密码
     *
     * @param username
     * @param newpassword
     * @param newrepeatpassword
     * @param power
     * @return
     */
    @RequestMapping(value = "adddadminaction.do", method = RequestMethod.POST)
    public ModelAndView addadmin(@RequestParam(value = "username", defaultValue = "0") String username,
                                 @RequestParam(value = "newpassword", defaultValue = "") String newpassword,
                                 @RequestParam(value = "newrepeatpassword", defaultValue = "") String newrepeatpassword,
                                 @RequestParam(value = "power", defaultValue = "2") String power,
                                 @RequestParam(value = "add", defaultValue = "1") String add) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin");
        mv.addObject("view", 3);
        if ("1".equals(add) && "admin".equals(session.getAttribute("username"))) {
            //只有admin用户可以添加登陆名
            mv.addObject("add", 1);
            if (StringUtil.isNullOrEmpty(username)) {
                mv.addObject("dto", new AdminModel());
                mv.addObject("info", "用户名不能为空");
                return mv;
            }
            if (username.length() < 5) {
                mv.addObject("dto", new AdminModel());
                mv.addObject("info", "用户名长度不能小于6");
                return mv;
            }
            AdminModel model = adminService.loginByName(username);
            if (null != model && model.getId() != 0) {
                mv.addObject("dto", new AdminModel());
                mv.addObject("info", "用户名重复了");
                return mv;
            }
            if (newpassword.length() < 6) {
                mv.addObject("dto", new AdminModel());
                mv.addObject("info", "密码不能小于6位数");
                return mv;
            }
            if (!newpassword.equals(newrepeatpassword)) {
                mv.addObject("dto", new AdminModel());
                mv.addObject("info", "重复密码和新密码不一致");
                return mv;
            }
            model = new AdminModel();
            model.setUsername(username);
            model.setPassword(newpassword);
            if ("1".equals(power)) {
                model.setPower(1);
            } else {
                model.setPower(2);
            }
            AdminModel newAdmin = adminService.addAdminModel(model);
            if (null != newAdmin && newAdmin.getId() > 0) {
                mv.addObject("dto", newAdmin);
                mv.addObject("info", "成功");
                mv.addObject("add", 2);
                return mv;
            }
            mv.addObject("dto", newAdmin);
            mv.addObject("info", "添加失败");
            return mv;
        }
        //修改权限
        if ("2".equals(add) && "admin".equals(session.getAttribute("username"))) {
            AdminModel adminModel = adminService.loginByName(username);
            mv.addObject("add", 2);
            if (adminModel.getId() > 0) {
                int p;
                try {
                    p = Integer.parseInt(power);
                    adminModel.setPower(p);
                    mv.addObject("dto", adminService.changeAdminModel(adminModel));
                    mv.addObject("info", "修改成功");
                    return mv;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mv.addObject("dto", adminModel);
                mv.addObject("info", "修改失败");
                return mv;
            }
            adminModel.setUsername(username);
            mv.addObject("dto", adminModel);
            mv.addObject("info", "用户不存在");
            return mv;
        }
        //修改密码
        mv.addObject("add", 3);
        if (username.equals(session.getAttribute("username"))) {
            if (newpassword.length() < 6) {
                AdminModel model = new AdminModel();
                model.setUsername(username);
                mv.addObject("dto", model);
                mv.addObject("info", "密码不能小于6位数");
                return mv;
            }
            if (!newpassword.equals(newrepeatpassword)) {
                AdminModel model = new AdminModel();
                model.setUsername(username);
                mv.addObject("dto", model);
                mv.addObject("info", "重复密码和新密码不一致");
                return mv;
            }
            AdminModel adminModel = new AdminModel();
            adminModel.setUsername(username);
            adminModel.setPassword(newpassword);
            AdminModel newAdmin = adminService.changeAdminModel(adminModel);
            if (null != newAdmin && newAdmin.getId() > 0) {
                mv.addObject("dto", newAdmin);
                mv.addObject("info", "修改成功");
                return mv;
            }
            mv.addObject("dto", newAdmin);
            mv.addObject("info", "修改失败");
            return mv;
        }
        mv.addObject("dto", new AdminModel());
        mv.addObject("info", "对不起您权限不够，请联系管理员");
        return mv;
    }
}
