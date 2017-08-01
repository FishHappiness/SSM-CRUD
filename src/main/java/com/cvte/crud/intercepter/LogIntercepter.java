package com.cvte.crud.intercepter;

import com.cvte.crud.bean.LogEntity;
import com.cvte.crud.exception.CustomException;
import com.cvte.crud.kafka.util.KafkaProducer;
import com.cvte.crud.warpper.HttpServletRequestCopier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

public class LogIntercepter implements HandlerInterceptor{
    private static Logger LOGGER = LoggerFactory.getLogger(LogIntercepter.class);
    private KafkaProducer kafkaProducer= new KafkaProducer();
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        HttpServletRequestCopier myRequestWrapper = new HttpServletRequestCopier((HttpServletRequest) httpServletRequest);

        String body = myRequestWrapper.getBody();
        System.out.println(body);
        long startTime = System.currentTimeMillis();
        httpServletRequest.setAttribute("startTime", startTime);
        if (handler instanceof HandlerMethod) {
            httpServletRequest.setAttribute("tranceId", UUID.randomUUID().toString());
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {
        LogEntity log = new LogEntity();
        String tranceId = (String) httpServletRequest.getAttribute("tranceId");
        if(tranceId==null || "".equals(tranceId)){
            httpServletRequest.setAttribute("tranceId",UUID.randomUUID().toString());
        }
        if(handler instanceof HandlerMethod){
            HandlerMethod h = (HandlerMethod) handler;
            log.setRequestMethod(h.getBean().getClass().getName());
            log.setMethodName(h.getMethod().getName());
            log.setUrl(httpServletRequest.getRequestURI());
            log.setParam(getParamString(httpServletRequest.getParameterMap()));
            log.setTranceId((String)httpServletRequest.getAttribute("tranceId"));
            long startTime = (Long) httpServletRequest.getAttribute("startTime");
            long endTime = System.currentTimeMillis();
            long executeTime = endTime - startTime;
            log.setElapsed(executeTime);
            httpServletRequest.setAttribute("log",log);
           // System.out.println(log.toString());
            //System.out.println(new ObjectMapper().writeValueAsString(log));
            if(executeTime > 500){
                LOGGER.warn("Time_OUT 500ms");
                kafkaProducer.produce("Time_OUT 500ms");
            }
        }
        //统一异常处理
        String message=null;
        CustomException customException = null;
        //如果e是系统自定义的异常，就直接取出异常信息
        if (e !=null && e instanceof CustomException) {
            customException = (CustomException) e;
            //错误信息
            message=customException.getMessage();
            LOGGER.error(message);
            kafkaProducer.produce(message);
            httpServletRequest.setAttribute("message",message);
            try {
                //转向到错误页面
                httpServletRequest.getRequestDispatcher("error.jsp").forward(httpServletRequest,httpServletResponse);
            } catch (ServletException se) {
                se.printStackTrace();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }else {
            customException = new CustomException("未知错误");
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
