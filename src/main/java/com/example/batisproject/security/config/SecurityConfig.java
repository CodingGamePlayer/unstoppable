package com.example.batisproject.security.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        String password = passwordEncoder().encode("1111");

        auth.inMemoryAuthentication().withUser("user").password(password).roles("USER");
//        auth.inMemoryAuthentication().withUser("manager").password(password).roles("MANAGER","USER");
        auth.inMemoryAuthentication().withUser("admin").password(password).roles("ADMIN","MANAGER","USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/login/**", "/login*",
                        "/**/*.svg", "/favicon.ico", "/**/*.png", "/**/*.gif",
                        "/**/*.svg", "/**/*.jpg", "/**/*.html", "/**/*.css","/**/*.js").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/manager/**").hasRole("MANAGER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        .and()
                .formLogin()

        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
