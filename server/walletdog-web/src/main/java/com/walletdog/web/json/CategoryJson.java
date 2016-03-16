package com.walletdog.web.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.walletdog.core.model.Category;

public class CategoryJson {
	
	private int categoryid;
    private String name;
    private String description;
    
    public CategoryJson() {}
    
    public CategoryJson(Category c) {
    	this.categoryid = c.categoryid;
    	this.name = c.name;
    	this.description = c.description;
    }
    
    public Category toCategory() {
    	return new Category(this.categoryid, this.name, this.description);
    }

    @JsonProperty
	public int getCategoryid() {
		return categoryid;
	}

    @JsonProperty
	public String getName() {
		return name;
	}

    @JsonProperty
	public String getDescription() {
		return description;
	}

}
