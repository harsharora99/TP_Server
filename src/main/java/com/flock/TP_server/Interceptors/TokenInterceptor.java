package com.flock.TP_server.Interceptors;

import com.flock.TP_server.Repositories.AuthTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    AuthTokenRepository authTokenRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // System.out.println("In the Pre Handle Interceptor");

        // Verify the AuthTokens
        String token = request.getHeader("Authorization");
        boolean isPresent = authTokenRepository.checkToken(token);
        if(isPresent) {
            return true;
        }

        response.setStatus(401);
        return false;
    }
}
