package com.tangquan.framework;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean jwtFilterRegistration(JWTFilter filter) {
        FilterRegistrationBean registrationBean = newFilterRegistrationBean(filter, 11);
        String[] patterns = new String[]{"/admin-user/*", "/auth-group/*", "/order-manage/*"};
        registrationBean.addUrlPatterns(patterns);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean corsFilterRegistration(SimpleCorsFilter filter) {
        FilterRegistrationBean registrationBean = newFilterRegistrationBean(filter, 9);
        String[] patterns = new String[]{"/*"};
        registrationBean.addUrlPatterns(patterns);
        return registrationBean;
    }

    private FilterRegistrationBean newFilterRegistrationBean(Filter filter, int order) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setAsyncSupported(false);
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST);
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setOrder(order);
        return filterRegistrationBean;
    }
}
