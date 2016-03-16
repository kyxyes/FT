package com.walletdog.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pair <T1, T2> {
	
	public T1 key;
	public T2 value;
	
	public Pair(T1 key, T2 value) {
		this.key = key;
		this.value = value;
	}

	@JsonProperty
	public T1 getKey() {
		return key;
	}

	@JsonProperty
	public T2 getValue() {
		return value;
	}

}
