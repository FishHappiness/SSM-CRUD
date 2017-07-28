package com.cvte.crud.intercepter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

public class LogIntercepter implements HandlerInterceptor{
    private static Logger LOGGER = LoggerFactory.getLogger(LogIntercepter.class);
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {

        long startTime = System.currentTimeMillis();
        httpServletRequest.setAttribute("startTime", startTime);
        
        if (handler instanceof HandlerMethod) {Thread.sleep(500);
            StringBuilder sb = new StringBuilder(1000);
            sb.append("\n"+"-----------------------").append(new Date()).append("-------------------------------------\n");
            HandlerMethod h = (HandlerMethod) handler;
            sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
            sb.append("Method    : ").append(h.getMethod().getName()).append("\n");
            String url = httpServletRequest.getRequestURI();
            String param = getParamString(httpServletRequest.getParameterMap());
            String[] params = null;
            if (url.lastIndexOf("/") != 0 && "".equals(param)) {
                 param = url.substring(url.lastIndexOf("/") + 1);
                 params = param.split("-");
            }
            sb.append("Params    : ").append((params == null? param:Arrays.asList(params))+"\n");
            sb.append("URI       : ").append(url).append("\n");
            System.out.print(sb.toString());
        }
        return true;
    }


    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {

        long startTime = (Long) httpServletRequest.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        if(handler instanceof HandlerMethod){
            StringBuilder sb = new StringBuilder(1000);
            sb.append("CostTime  : ").append(executeTime).append("ms").append("\n");
            System.out.print(sb.toString());
        }
        if(executeTime > 500){
            LOGGER.warn("Time_OUT 500ms");
        }
    }
    private String getParamString(Map<String, String[]> map) {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String,String[]> e:map.entrySet()){
            sb.append(e.getKey()).append("=");
            String[] value = e.getValue();
            if(value != null && value.length == 1){
                sb.append(value[0]).append("\t");
            }else{
                sb.append(Arrays.toString(value)).append("\t");
            }
        }
        return sb.toString();
    }
}
