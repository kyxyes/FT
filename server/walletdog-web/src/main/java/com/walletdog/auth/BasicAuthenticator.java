package com.walletdog.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import com.google.common.base.Optional;
import com.walletdog.api.WalletdogApi;
import com.walletdog.core.model.User;

public class BasicAuthenticator implements Authenticator<BasicCredentials, User> {
	
	private WalletdogApi api;

    public BasicAuthenticator(WalletdogApi api) {
        this.api = api;
    }

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        java.util.Optional<User> result = api.findUserAccountByEmail(credentials.getUsername());
        if (result.isPresent()) {
            User u = result.get();
            if (User.validatePassword(credentials.getPassword(), u)) {
                return Optional.of(u);
            } else {
                return Optional.absent();
            }
        } else {
            return Optional.absent();
        }
    }

}
