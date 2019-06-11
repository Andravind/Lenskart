package com.example.lenskart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExcludeList {
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("filter_id")
    @Expose
    private String filterId;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getFilterId() {
        return filterId;
    }

    public void setFilterId(String filterId) {
        this.filterId = filterId;
    }
}
