package com.example.batisproject.security.provider;

import com.example.batisproject.security.common.FormWebAuthenticationDetails;
import com.example.batisproject.security.service.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String password = (String)authentication.getCredentials();


        UserContext userContext = (UserContext) userDetailsService.loadUserByUsername(email);

        log.info("==================================");
        log.info("email : " + userContext.getUser().getUsername());
        log.info("password : " + userContext.getUser().getPassword());

        if(!passwordEncoder.matches(password, userContext.getUser().getPassword())){
            throw new BadCredentialsException("비밀번호가 일치 하지 않습니다.");
        }

        FormWebAuthenticationDetails formWebAuthenticationDetails = (FormWebAuthenticationDetails) authentication.getDetails();
        String secretKey = formWebAuthenticationDetails.getSecretKey();

        if(secretKey == null || !"우지당만112233445566778899".equals(secretKey)){
            throw new InsufficientAuthenticationException("인증이 되지 않은 경로로 접근 하였습니다.");
        }

        return new UsernamePasswordAuthenticationToken(userContext.getUser(), null , userContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
