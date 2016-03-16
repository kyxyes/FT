package com.walletdog.web;

import java.util.Collections;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class WalletdogConfiguration extends Configuration {

	@NotEmpty
    private String database;
	
	@NotNull
	private Map<String, String> sqlInitStatements = Collections.emptyMap();

	@JsonProperty
	public String getDatabase() {
		return database;
	}

	@JsonProperty
	public void setDatabase(String database) {
		this.database = database;
	}
	
	@JsonProperty("sql")
	public Map<String, String> getSqlInitStatements() {
		return sqlInitStatements;
	}

	@JsonProperty("sql")
	public void setSqlInitStatements(
			Map<String, String> sqlInitStatements) {
		this.sqlInitStatements = sqlInitStatements;
	}
}
