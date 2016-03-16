package com.walletdog.auth;

import io.dropwizard.auth.Authorizer;

import com.walletdog.core.model.User;

public class BasicAuthorizer implements Authorizer<User> {
	
	@Override
    public boolean authorize(User principal, String role) {
        return role.equals(principal.getRole());
    }

}
