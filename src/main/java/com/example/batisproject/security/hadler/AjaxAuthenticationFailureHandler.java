package com.example.batisproject.security.hadler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException exception) throws IOException, ServletException {

        String errorMessage = "Invalid Username or Password";

        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.addHeader("Content-Type", "application/json; charset=UTF-8");

        if(exception instanceof BadCredentialsException) {
            errorMessage = "아이디와 비밀번호가 틀립니다.";

        } else if (exception instanceof DisabledException) {
            errorMessage = "인증되지 않은 경로로 접근하였습니다.";

        } else if (exception instanceof CredentialsExpiredException) {
            errorMessage = "패스워드가 만료되었습니다.";
        }

        objectMapper.writeValue(httpServletResponse.getWriter(),errorMessage);
    }
}
