package com.zhaopin.core.filter;

import com.zhaopin.core.util.StringUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogonFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;

        String actionUrl = httpReq.getServletPath();

        HttpSession session = httpReq.getSession();
        String seesionusername = (String) session.getAttribute("username");

        String power = (String) session.getAttribute("power");
        String username = httpReq.getParameter("username");
        String password = httpReq.getParameter("password");
        //判断用户是否是进行管理员操作-非管理员权限不能登陆到admin.do中
        if ((null != actionUrl && actionUrl.equals("/admin.do") && "1".equals(power))) {
            chain.doFilter(httpReq, response);
        } else {
            if ((actionUrl != null && actionUrl.equals("/login.do")) ||
                    (!StringUtil.isNullOrEmpty(username) && !StringUtil.isNullOrEmpty(password)) ||
                    !StringUtil.isNullOrEmpty(seesionusername)) {
                chain.doFilter(httpReq, response);
            } else {
                // 否则，会跳转到提示信息页
                ((HttpServletResponse) response).sendRedirect(httpReq
                        .getContextPath()
                        + "/login.do");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
