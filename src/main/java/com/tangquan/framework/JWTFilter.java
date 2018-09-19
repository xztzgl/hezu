package com.tangquan.framework;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.tangquan.framework.JWTHelper.AUTH_TYPE;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTFilter.class);
    @Autowired
    private JWTHelper jwtHelper;
    private String errMsg = "{\"key\":\"UNAUTHORIZED\",\"msg\":\"Need Authorization\"}";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if (servletPath.startsWith("/v2/api-docs") || servletPath.startsWith("/swagger-resources") ||
                servletPath.startsWith("/swagger-ui.html") || servletPath.contains("swagger") ||
                request.getMethod().equals("OPTIONS") || servletPath.contains("login")) {
            filterChain.doFilter(request, response);
            return;
        }
        String jwtStr = request.getHeader(AUTHORIZATION);
        if (StringUtils.isEmpty(jwtStr)) {
            LOGGER.error("{} 不能为空", AUTHORIZATION);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(errMsg);
        } else if (!jwtStr.startsWith(AUTH_TYPE)) {
            LOGGER.error("token格式不正确, token: {}", jwtStr);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(errMsg);
        } else {
            jwtStr = jwtStr.substring(AUTH_TYPE.length());
            Jws<Claims> jwt;
            try {
                jwt = jwtHelper.parseJWT(jwtStr);
                request.setAttribute("BankCode", jwt.getBody().getSubject());
            } catch (RuntimeException e) {
                LOGGER.error("token不合法", e);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getWriter().write(errMsg);
                return;
            }
            if (jwtHelper.needRefresh(jwt)) {
                String newJwtStr = jwtHelper
                        .createJWT(jwt.getBody().getId(), jwt.getBody().getIssuer(), jwt.getBody().getSubject());
                response.addHeader(AUTHORIZATION, AUTH_TYPE + newJwtStr);
            }
            filterChain.doFilter(request, response);
        }
    }
}
