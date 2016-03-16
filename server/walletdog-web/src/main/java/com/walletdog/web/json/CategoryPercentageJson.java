package com.walletdog.web.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.walletdog.core.model.CategoryPercentage;

/**
 * Created by hpishepei on 11/19/15.
 * Modified by Ke Wang on 11/19/15
 */
public class CategoryPercentageJson {

    private int categoryId;
    private String categoryName;
    private double percentage;

    public CategoryPercentageJson() {}

    public CategoryPercentageJson(CategoryPercentage c) {
        this.categoryId = c.categoryId;
        this.categoryName = c.categoryName;
        this.percentage = c.percentage;
    }

    public CategoryPercentage toCategoryPercentage() {
        return new CategoryPercentage(this.categoryId, this.categoryName, this.percentage);
    }

    @JsonProperty
    public int getCategoryId() {
        return categoryId;
    }

    @JsonProperty
    public String getCategoryName() {
        return categoryName;
    }

    @JsonProperty
    public double getPercentage() {
        return percentage;
    }
}
