package com.abraham.testinventapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class CityModel {
    @SerializedName("StatusCode")
    private String StatusCode;
    @SerializedName("Value")
    private List<CityValue> Value;

    @Data
    public class CityValue {
        @SerializedName("CityID")
        private String CityID;
        @SerializedName("CityName")
        private String CityName;
        @SerializedName("ProvinceID")
        private String ProvinceID;
    }
}
