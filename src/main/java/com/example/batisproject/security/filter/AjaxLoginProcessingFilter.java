package com.example.batisproject.security.filter;

import com.example.batisproject.dto.UserDTO;
import com.example.batisproject.security.token.AjaxAuthenticationToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private ObjectMapper objectMapper = new ObjectMapper();

    public AjaxLoginProcessingFilter() {
        super(new AntPathRequestMatcher("/api/login"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if(!isAjax(request)){
            throw new IllegalStateException("Ajax 인증방식이 아닙니다.");
        }

        UserDTO userDTO = objectMapper.readValue(request.getReader(), UserDTO.class);

        if (userDTO.getUsername().equals("") || userDTO.getPassword().equals("")){
            throw new IllegalArgumentException("아이디 또는 패스워드 값을 입력하지 않았습니다.");
        }

        AjaxAuthenticationToken ajaxAuthenticationToken =
                new AjaxAuthenticationToken(userDTO.getUsername(), userDTO.getPassword());


        return getAuthenticationManager().authenticate(ajaxAuthenticationToken);
    }

    private boolean isAjax(HttpServletRequest request) {

        if("Unstoppable".equals(request.getHeader("X-Requested-with"))){
            return true;
        }
        return false;
    }
}
