package com.cvte.crud.Filter;

import com.cvte.crud.warpper.HttpServletRequestCopier;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestFilter implements Filter {

    public void destroy() {
        System.out.println("filter destroy...");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


    }
    public void init(FilterConfig arg0) throws ServletException {
        System.out.println("init filter...");
    }
}
