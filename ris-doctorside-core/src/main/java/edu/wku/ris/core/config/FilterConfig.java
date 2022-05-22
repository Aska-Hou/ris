package edu.wku.ris.core.config;

import edu.wku.ris.core.adapter.DefaultFilterPathAdapter;
import edu.wku.ris.core.adapter.FilterPathAdapter;
import edu.wku.ris.core.controller.auth.filter.AuthorizationFilter;
import edu.wku.ris.core.controller.auth.filter.SwaggerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.servlet.DispatcherType;
import java.util.Collections;

/**
 * @author Aska
 * @description Function Description
 * @create 2022/4/25 19:35
 */
@Configuration
public class FilterConfig {

    @Autowired
    private AuthorizationFilter authFilter;

    @Autowired
    private SwaggerRequestFilter swaggerRequestFilter;

    @Autowired
    public FilterPathAdapter filterPathAdapter;

    @Bean
    @ConditionalOnMissingBean
    public FilterPathAdapter getFilterPathAdapter(){
        return new DefaultFilterPathAdapter();
    }

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> authorizationFilterFilterRegistrationBean() {
        FilterRegistrationBean<AuthorizationFilter> registration = new FilterRegistrationBean<>();
        // add filter
        registration.setFilter(authFilter);
        // set filter rule
        if (filterPathAdapter != null) {
            registration.addUrlPatterns(filterPathAdapter.getDefaultPath().toArray(new String[0]));
        }
        registration.setName("authFilter");
        // set order
        registration.setOrder(0);
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<SwaggerRequestFilter> swaggerRequestFilterFilterRegistrationBean() {
        FilterRegistrationBean<SwaggerRequestFilter> registration = new FilterRegistrationBean<>();
        // add filter
        registration.setFilter(swaggerRequestFilter);
        // set filter rule
        if (filterPathAdapter != null) {
            registration.addUrlPatterns(filterPathAdapter.getDefaultPath().toArray(new String[0]));
        }
        registration.setName("swaggerFilter");
        // Set Order
        registration.setOrder(1);
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        return registration;
    }
}
