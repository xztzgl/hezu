package com.tangquan.framework;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean jwtFilterRegistration(JWTFilter filter) {
        FilterRegistrationBean registrationBean = newFilterRegistrationBean(filter, 11);
        String[] patterns = new String[]{
            "/admin-user/*",
            "/auth-group/*",
            "/order-manage/*",
            "/server-web-management/*",
            "/server-web-statistic/*",
            "/upload/*",
            "/wechat-favorite/*",
            "/wechat-house/add",
            "/wechat-house/delete/{id}",
            "/wechat-house/update",
            "/wechat-my/*",
            "/wechat-notice/*",
            "/wechat-order/*",
            "/wechat-publish/*",
            "/wechat-person/add",
            "/wechat-person/delete/{id}",
            "/wechat-person/update"
        };
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
