package com.example.arrestmanagement.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorsPropertiesEnum {
    TECHNICAL_ERROR(3),
    BUSINESS_ERROR(5),
    ;
    private final int code;


}
