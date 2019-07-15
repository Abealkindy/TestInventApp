package com.abraham.testinventapp.models;

import lombok.Data;

@Data
public class RequestProvinceIDModel {
    private String ProvinceID;

    public RequestProvinceIDModel(String provinceID) {
        ProvinceID = provinceID;
    }
}
