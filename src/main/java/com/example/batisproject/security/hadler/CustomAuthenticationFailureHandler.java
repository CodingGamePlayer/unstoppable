package com.example.batisproject.security.hadler;

import org.apache.http.entity.StringEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String errorMassage = "";

        if(exception instanceof BadCredentialsException) {
            errorMassage = "아이디와비밀번호가틀립니다.";

        } else if (exception instanceof InsufficientAuthenticationException) {
            errorMassage = "인증되지 않은 경로로 접근하였습니다.";
        }

        String encode = URLEncoder.encode(errorMassage, "UTF-8");
        setDefaultFailureUrl("/login?error=true&exception=" + encode);

        super.onAuthenticationFailure(request, response, exception);
    }
}
