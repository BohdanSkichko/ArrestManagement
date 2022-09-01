package com.example.arrestmanagement.helper;

public enum ErrorsPropertiesEnum {
    TECHNICAL_ERROR("TECHNICAL_ERROR"),
    BUSINESS_ERROR("BUSINESS_ERROR"),
    ;
    private final String path;

    ErrorsPropertiesEnum(String path) {
        this.path = path;
    }


    public String getPath() {
        return (PropertiesReader.getProperties(this.path));
    }


}
