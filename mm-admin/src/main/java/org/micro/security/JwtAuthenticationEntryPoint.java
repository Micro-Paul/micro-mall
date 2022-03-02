package org.micro.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author micro-paul
 * @date 2022年02月10日 9:54
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //未登录 当用户尝试访问安全的REST资源而不提供任何凭据时，将调用此方法发送401 响应
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e == null ? "未登录" : e.getMessage());
    }
}
