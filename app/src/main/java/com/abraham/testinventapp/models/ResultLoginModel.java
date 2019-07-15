package com.abraham.testinventapp.models;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ResultLoginModel {
    @SerializedName("Title")
    private String Title;
    @SerializedName("StatusCode")
    private String StatusCode;
    @SerializedName("StatusMessage")
    private String StatusMessage;

}
