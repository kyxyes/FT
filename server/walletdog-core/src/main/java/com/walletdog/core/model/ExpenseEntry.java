package com.walletdog.core.model;

/**
 * entry for every personal expense
 * @author wangke
 *
 */
public class ExpenseEntry {

	/**
	 * {
		    "user_id": int
		    "entry_id":
		    "amount": double
		    "date": YYYY-MM-DD HH:MM:SS.SSS
		    "category_id": int
		    "location":
		    "description": 
		}
	 */
	public final int userid;
	public final int entryid;
	public final double amount;
	public final String date;
	public final int categoryid;
	public final String location;
	public final String description;
	
	public ExpenseEntry(int entryId, int userId, double amount, String date, int categoryId, String location, String description) {
		this.userid = userId;
		this.entryid = entryId;
		this.amount = amount;
		this.date = date;
		this.categoryid = categoryId;
		this.location = location;
		this.description = description;
	}
	
	public String toString() {
        return String.format("ExpenseEntry(%d, %d, %f, %s, %d, %s, %s)", entryid, userid, amount, date, categoryid, location, description);
    }
}
