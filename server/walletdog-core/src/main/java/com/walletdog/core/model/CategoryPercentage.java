package com.walletdog.core.model;

public class CategoryPercentage {

    /**
     * {
     "catogery_id": int
     "catogery_name": String
     "percentage" : double
     }
     */

    public int categoryId;
    public final String categoryName;
    public final double percentage;


    public CategoryPercentage(int categoryId, String categoryName, double percentage) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.percentage = percentage;

    }

    public String toString() {
        return String.format("CategoryPercentage(%d, %s, %f)", categoryId, categoryName, percentage);
    }
}
