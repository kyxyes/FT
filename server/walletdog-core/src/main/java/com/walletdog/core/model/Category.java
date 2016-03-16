package com.walletdog.core.model;

public class Category {
	
	public int categoryid;
    public final String name;
    public final String description;

    public Category(String name, String description){
        this.name = name;
        this.description = description;
    }
    
    public Category(int categoryid, String name, String description){
        this.categoryid = categoryid;
        this.name = name;
        this.description = description;
    }
    
    public String toString() {
        return String.format("Category(%d, %s, %s)", categoryid, name, description);
    }
}
