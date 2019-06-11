package com.example.lenskart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("exclude_list")
    @Expose
    private List<List<ExcludeList>> excludeList = null;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<List<ExcludeList>> getExcludeList() {
        return excludeList;
    }

    public void setExcludeList(List<List<ExcludeList>> excludeList) {
        this.excludeList = excludeList;
    }

}
