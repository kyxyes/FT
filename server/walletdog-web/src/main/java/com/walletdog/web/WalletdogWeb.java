package com.walletdog.web;

import com.walletdog.api.WalletdogApi;
import com.walletdog.api.dao.WalletdogDAO;
import com.walletdog.api.dao.impl.SqliteDAO;
import com.walletdog.auth.BasicAuthenticator;
import com.walletdog.auth.BasicAuthorizer;
import com.walletdog.core.model.User;
import com.walletdog.filter.CORSFilter;
import com.walletdog.health.WalletdogHealthCheck;
import com.walletdog.web.resource.CategoryResource;
import com.walletdog.web.resource.ExpenseResource;
import com.walletdog.web.resource.UserResource;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Environment;

public class WalletdogWeb extends Application<WalletdogConfiguration>{
	public static void main(String[] args) throws Exception {
		new WalletdogWeb().run(args);
	}

	@Override
	public void run(WalletdogConfiguration config, Environment environment)
			throws Exception {
		
		final WalletdogDAO dao = new SqliteDAO(config.getDatabase(), config.getSqlInitStatements());
        final WalletdogApi api = new WalletdogApi(dao);
        
        final WalletdogHealthCheck healthCheck = new WalletdogHealthCheck();
        environment.healthChecks().register("walletdog", healthCheck);
        
        final UserResource userResource = new UserResource(api);
        final CategoryResource categoryResource = new CategoryResource(api);
        final ExpenseResource expenseResource = new ExpenseResource(api);
        
        environment.jersey().register(userResource);
        environment.jersey().register(categoryResource);
        environment.jersey().register(expenseResource);
        
        environment.jersey().setUrlPattern("/api/*");
        environment.jersey().register(
                new AuthDynamicFeature(
                        new BasicCredentialAuthFilter.Builder<User>()
                                .setAuthenticator(new BasicAuthenticator(api))
                                .setAuthorizer(new BasicAuthorizer())
                                .setRealm("ADMIN USER")
                                .buildAuthFilter())
        );
        environment.jersey().register(CORSFilter.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
	}

}
