package com.example.batisproject.security.common;

import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

public class FormWebAuthenticationDetails extends WebAuthenticationDetails {
    
    private String secretKey;

    public String getSecretKey() {
        return secretKey;
    }

    public FormWebAuthenticationDetails(HttpServletRequest request) {
        super(request);

        this.secretKey = request.getParameter("secret_key");


    }
}
