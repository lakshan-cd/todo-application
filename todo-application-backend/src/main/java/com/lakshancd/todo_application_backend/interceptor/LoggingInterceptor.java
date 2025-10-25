package com.lakshancd.todo_application_backend.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.ThreadMXBean;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Health check Logger need to be added
        // Environment Logger need to be added
        //Resource Utilization Logger need to be added
        // BeanInitialization Logger need to be added
        //Security Audit Loggers need to be added
        logMemoryUsage();
        logThreadCount();
        String correlationId = (String) request.getAttribute("CORRELATION-ID");
        log.info("CORRELATION-ID: {}; Method: {}; URI: {}; RemoteAddr: {}; Headers: {};",
                correlationId, request.getMethod(), request.getRequestURI(), request.getRemoteAddr(), request.getHeaderNames().toString());
        return true;
    }


    private void logMemoryUsage() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryBean.getHeapMemoryUsage();

        Map<String, Object> memoryInfo = new LinkedHashMap<>();
        memoryInfo.put("HeapMemoryUsage", Map.of(
                "Max", heapMemoryUsage.getMax(),
                "Used", heapMemoryUsage.getUsed(),
                "Committed", heapMemoryUsage.getCommitted()
        ));

        String jsonMemoryInfo = convertToJsonString(memoryInfo);
        log.info("Memory Usage Information: {}", jsonMemoryInfo);
    }
    private String convertToJsonString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Error converting object to JSON string", e);
            return "{}";
        }
    }
    private void logThreadCount() {
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        Map<String, Object> threadInfo = new LinkedHashMap<>();
        threadInfo.put("ThreadCount", Map.of(
                "Current", threadBean.getThreadCount(),
                "Peak", threadBean.getPeakThreadCount()
        ));
        String jsonThreadInfo = convertToJsonString(threadInfo);
        log.info("Thread Count Information: {}", jsonThreadInfo);
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //Audit Logger
        String correlationId = (String) request.getAttribute("CORRELATION-ID");
        //log.info("CORRELATION-ID: {}; BufferSize: {}; Session: {}; URI: {};Headers: {};",
             //   correlationId, response.getBufferSize(), request.getSession(), request.getRemoteAddr(), request.getHeaderNames().toString());
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Nothing Executed
        MDC.remove("CORRELATION-ID");
    }
}
