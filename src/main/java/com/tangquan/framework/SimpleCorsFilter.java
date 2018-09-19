package com.tangquan.framework;

import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletRequest;

@Component
public class SimpleCorsFilter extends CorsFilter {

    public SimpleCorsFilter() {
        super(newCorsConfigurationSource());
    }

    private static CorsConfigurationSource newCorsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedMethod("*");
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);
        return request -> config;
    }
}