package com.example.lenskart.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Filter {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("default")
    @Expose
    private Integer _default;
    @SerializedName("id")
    @Expose
    private String id;

    private Boolean isSelectable = true;

    private String unSelectableId;

    public String getUnSelectableId() {
        return unSelectableId;
    }

    public void setUnSelectableId(String unSelectableId) {
        this.unSelectableId = unSelectableId;
    }

    public Boolean getSelectable() {
        return isSelectable;
    }
    public void setIsSelectable(){
        this.isSelectable=true;
    }

    public void setSelectable(Boolean selectable) {
        isSelectable = selectable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDefault() {
        return _default;
    }

    public void setDefault(Integer _default) {
        this._default = _default;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
