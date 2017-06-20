package com.zhaopin.core.filter;

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
        String flag = (String) session.getAttribute("username");

        String username = httpReq.getParameter("username");
        String password = httpReq.getParameter("password");

        if ((actionUrl != null && actionUrl.equals("/login.do"))
                || flag != null
                || (username != null && password != null)) {
            chain.doFilter(httpReq, response);
        } else {
            // 否则，会跳转到提示信息页
            ((HttpServletResponse) response).sendRedirect(httpReq
                    .getContextPath()
                    + "/login.do");
        }
    }

    @Override
    public void destroy() {

    }
}
