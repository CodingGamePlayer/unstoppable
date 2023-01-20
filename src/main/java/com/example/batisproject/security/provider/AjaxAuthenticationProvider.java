package com.example.batisproject.security.provider;

import com.example.batisproject.security.common.FormWebAuthenticationDetails;
import com.example.batisproject.security.service.UserContext;
import com.example.batisproject.security.token.AjaxAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class AjaxAuthenticationProvider  implements AuthenticationProvider {


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional
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


        return new AjaxAuthenticationToken(userContext.getUser(), null , userContext.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(AjaxAuthenticationToken.class);
    }
}
