package com.zempty.spring_skill_learn.filter;

import javax.servlet.*;
import java.io.IOException;

public class ThirdFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(chain);
        chain.doFilter(request,response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(filterConfig);
    }

    @Override
    public void destroy() {
        System.out.println("test3..........");
    }
}
