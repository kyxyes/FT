package com.walletdog.web.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.walletdog.core.model.ExpenseEntry;

public class ExpenseJson {

	private int userid;
	private int entryid;
	private double amount;
	private String date;
	private int categoryid;
	private String location;
	private String description;
	
	public ExpenseJson() {}
    
    public ExpenseJson(ExpenseEntry e) {
    	this.userid = e.userid;
    	this.entryid = e.entryid;
    	this.amount = e.amount;
    	this.date = e.date;
    	this.categoryid = e.categoryid;
    	this.location = e.location;
    	this.description = e.description;
    }
    
    public ExpenseEntry toExpenseEntry() {
    	return new ExpenseEntry(this.entryid, this.userid, this.amount, this.date, this.categoryid, this.location, this.description);
    }

    @JsonProperty
	public int getUserid() {
		return userid;
	}

    @JsonProperty
	public int getEntryid() {
		return entryid;
	}

    @JsonProperty
	public double getAmount() {
		return amount;
	}

    @JsonProperty
	public String getDate() {
		return date;
	}

    @JsonProperty
	public int getCategoryid() {
		return categoryid;
	}

    @JsonProperty
	public String getLocation() {
		return location;
	}

    @JsonProperty
	public String getDescription() {
		return description;
	}
}