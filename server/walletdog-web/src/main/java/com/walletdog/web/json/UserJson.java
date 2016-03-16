package com.walletdog.web.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.walletdog.core.model.User;

public class UserJson {
	
	private int userid;
	private String password;
	private String email;
	private String username;
	
	public UserJson() {}
	
	public UserJson(User user) {
		this.userid = user.userid;
		this.password = user.password;
		this.email = user.email;
		this.username = user.username;
	}
	
	public User toUser() {
		return new User(this.userid, this.email, this.password, this.username);
	}

	@JsonProperty
	public int getUserid() {
		return userid;
	}

	@JsonProperty
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public String getEmail() {
		return email;
	}

	@JsonProperty
	public String getUsername() {
		return username;
	}

}
