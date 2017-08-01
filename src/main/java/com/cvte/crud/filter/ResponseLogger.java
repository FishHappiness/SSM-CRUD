
package com.cvte.crud.Filter;

import com.cvte.crud.bean.LogEntity;
import com.cvte.crud.kafka.util.KafkaProducer;
import com.cvte.crud.warpper.HttpServletResponseCopier;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;

public class ResponseLogger implements Filter{
    private static Logger LOGGER = LoggerFactory.getLogger(ResponseLogger.class);
    protected ServletRequest request;
    private ServletResponse response;
    private FilterChain chain;
    private KafkaProducer kafkaProducer= new KafkaProducer();
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
            LogEntity log = null;
            ObjectMapper objectMapper = new ObjectMapper();
            String JSON = null;
            try {
                if( request.getAttribute("log") != null) {
                    log = (LogEntity) request.getAttribute("log");
                    log.setResult(new String(copy, response.getCharacterEncoding()));
                }
                JSON = objectMapper.writeValueAsString(log);
                System.out.println(JSON);
                LOGGER.info(JSON);
                kafkaProducer.produce(JSON);
            }catch (Exception e){
                LOGGER.error(e.getMessage());
            }
        }
    }
    public void destroy() {
    }
}
