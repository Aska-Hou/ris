package edu.wku.ris.core.controller.auth.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/27 9:59
 */
@Component
@Slf4j
public class SwaggerRequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpServletRequest.getRequestURI();
        if (requestURI.contains("swagger-ui")) {
            log.info(" -- Swagger Request -- ");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
