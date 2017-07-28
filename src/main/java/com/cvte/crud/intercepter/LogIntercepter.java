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
import java.util.UUID;

public class LogIntercepter implements HandlerInterceptor{
    private static Logger LOGGER = LoggerFactory.getLogger(LogIntercepter.class);
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {

        long startTime = System.currentTimeMillis();
        httpServletRequest.setAttribute("startTime", startTime);
        
        if (handler instanceof HandlerMethod) {
            Thread.sleep(500);
            httpServletRequest.setAttribute("tranceId", UUID.randomUUID().toString());
            StringBuilder builder = new StringBuilder(1000);
            builder.append("\n"+"-----------------------").append(new Date()).append("-------------------------------------\n");
            HandlerMethod h = (HandlerMethod) handler;
            builder.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
            builder.append("Method    : ").append(h.getMethod().getName()).append("\n");
            String url = httpServletRequest.getRequestURI();
            String param = getParamString(httpServletRequest.getParameterMap());
            String[] params = null;
            if (url.lastIndexOf("/") != 0 && "".equals(param)) {
                 param = url.substring(url.lastIndexOf("/") + 1);
                 params = param.split("-");
            }
            builder.append("Params    : ").append("pn="+(params == null? param:Arrays.asList(params))+"\n");
            builder.append("URI       : ").append(url).append("\n");
            System.out.print(builder.toString());
        }
        return true;
    }


    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {

        StringBuilder builder = new StringBuilder(1000);
        String tranceId = (String) httpServletRequest.getAttribute("tranceId");
        if(tranceId==null || "".equals(tranceId)){
            httpServletRequest.setAttribute("tranceId",UUID.randomUUID().toString());
        }
        builder.append("tranceId : "+tranceId+"\n");
        long startTime = (Long) httpServletRequest.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        if(handler instanceof HandlerMethod){
            builder.append("CostTime  : ").append(executeTime).append("ms").append("\n");
            System.out.print(builder.toString());
        }
        if(executeTime > 500){
            LOGGER.warn("Time_OUT 500ms");
        }
    }
    private String getParamString(Map<String, String[]> map) {
        StringBuilder builder = new StringBuilder();
        for(Map.Entry<String,String[]> e:map.entrySet()){
            builder.append(e.getKey()).append("=");
            String[] value = e.getValue();
            if(value != null && value.length == 1){
                builder.append(value[0]).append("\t");
            }else{
                builder.append(Arrays.toString(value)).append("\t");
            }
        }
        return builder.toString();
    }
}
