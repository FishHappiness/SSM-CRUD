package com.cvte.crud.filter;


import com.cvte.crud.warpper.HttpServletResponseCopier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;

public class ResponseFilter implements Filter{
    private static Logger LOGGER = LoggerFactory.getLogger(ResponseFilter.class);
    protected ServletRequest request;
    private ServletResponse response;
    private FilterChain chain;

    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
        this.request = request;
        this.response = response;
        this.chain = chain;
        if (response.getCharacterEncoding() == null) {
            response.setCharacterEncoding("UTF-8");
        }
        HttpServletResponseCopier responseCopier = null;
        try {
            responseCopier = new HttpServletResponseCopier((HttpServletResponse) response);
            chain.doFilter(request, responseCopier);
            responseCopier.flushBuffer();
        } catch(Exception e){
            LOGGER.error(e.getMessage());
        }finally {
            byte[] copy = responseCopier.getCopy();
            try {
                System.out.println("return :"+new String(copy, response.getCharacterEncoding()));
                LOGGER.info("return :"+new String(copy, response.getCharacterEncoding()));
            }catch (Exception e){
                LOGGER.error(e.getMessage());
            }

        }
    }
    public void destroy() {
    }
}
