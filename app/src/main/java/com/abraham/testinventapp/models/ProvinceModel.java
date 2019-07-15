package com.abraham.testinventapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class ProvinceModel {
    @SerializedName("StatusCode")
    private String StatusCode;
    @SerializedName("Value")
    private List<ProvinceValue> Value;

    @Data
    public class ProvinceValue {
        @SerializedName("ProvinceID")
        private String ProvinceID;
        @SerializedName("ProvinceName")
        private String ProvinceName;
    }
}
