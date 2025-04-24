package com.alten.alten_shop.security.config;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class CustomSecurity {
    private String grantedAccount = "admin@admin.com";

    public AuthorizationDecision checkEmail(Authentication authentication) {
        String email = authentication.getName();

        boolean granted = grantedAccount.equals(email);
        return new AuthorizationDecision(granted);
    }
}
