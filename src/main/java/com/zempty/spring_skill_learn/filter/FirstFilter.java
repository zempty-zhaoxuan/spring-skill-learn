package com.zempty.spring_skill_learn.filter;


import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
@Order(1)
public class FirstFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(filterConfig);

    }

    @Override
    public void destroy() {
        System.out.println("test ........");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 要继续处理请求，必须添加 filterChain.doFilter()
        System.out.println(chain);
        chain.doFilter(request,response);
    }
}
