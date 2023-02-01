package com.example.batisproject.security.config;

import com.example.batisproject.security.hadler.CustomAccessDeniedHandler;
import com.example.batisproject.security.hadler.CustomAuthenticationFailureHandler;
import com.example.batisproject.security.hadler.CustomAuthenticationSuccessHandler;
import com.example.batisproject.security.provider.CustomAuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter{



    @Autowired
    private AuthenticationDetailsSource authenticationDetailsSource;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        web.ignoring().requestMatchers(new AntPathRequestMatcher("/fonts/"));
        web.ignoring().antMatchers("/api-doc",  "/configuration/ui","/swagger-resources", "/configuration/security","/swagger-ui.html", "/webjars/**","/swagger/**");
        web.ignoring().antMatchers("/**/*.svg", "/favicon.ico", "/**/*.png", "/**/*.gif","/**/*.svg", "/**/*.jpg", "/**/*.html", "/**/*.css","/**/*.js");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/login/**", "/api/**", "/signup").permitAll()
                .antMatchers("/user/**","/articles", "/article/**").access("hasRole('USER') or hasRole('ADMIN')")
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                .anyRequest().authenticated()
        .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login_proc")
                .authenticationDetailsSource(authenticationDetailsSource)
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .permitAll();

        http
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                ;

    }


    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        AccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
        ((CustomAccessDeniedHandler) accessDeniedHandler).setErrorPage("/denied");
        return accessDeniedHandler; 
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }


}
