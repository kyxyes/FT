package com.walletdog.health;

import com.codahale.metrics.health.HealthCheck;

public class WalletdogHealthCheck extends HealthCheck {

	@Override
	protected Result check() throws Exception {
		return Result.healthy();
	}

}
