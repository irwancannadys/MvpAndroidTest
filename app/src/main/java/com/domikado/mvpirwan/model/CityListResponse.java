package com.domikado.mvpirwan.model;
// Created by Arif Ariyan (me@arifariyan.com) on 1/9/17.

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CityListResponse {


    @SerializedName("data")
    @Expose
    private List<CityListData> data = new ArrayList<CityListData>();

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("status")
    @Expose
    private Integer status;

    public List<CityListData> getData() {
        return data;
    }

    public void setData(List<CityListData> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
