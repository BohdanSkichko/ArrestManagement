package com.example.arrestmanagement.helper;

public enum PropertiesEnum {
    FNS("FNS"),
    FSSP("FSSP"),
    FNS_PASSPORT("FNS_PASSPORT"),
    FNS_FOREIGN_PASSPORT("FNS_FOREIGN_PASSPORT"),
    FSSP_PASSPORT("FSSP_PASSPORT"),
    FSSP_FOREIGN_PASSPORT("FSSP_FOREIGN_PASSPORT"),
    CLIENT_DUL_TYPE_PASSPORT("CLIENT_DUL_TYPE_PASSPORT"),
    CLIENT_DUL_TYPE_FOREIGN_PASSPORT("CLIENT_DUL_TYPE_FOREIGN_PASSPORT"),
    TECHNICAL_ERROR("TECHNICAL_ERROR"),
    BUSINESS_ERROR("BUSINESS_ERROR"),
    FIRST_OPERATION("FIRST_OPERATION"),
    EDIT_ARREST("EDIT_ARREST"),
    CANCELED_ARREST("CANCELED_ARREST"),
    ;

    private final String path;

    public String getPath() {
        return (PropertiesReader.getProperties(this.path));
    }

    PropertiesEnum(String path) {
        this.path = path;
    }
}