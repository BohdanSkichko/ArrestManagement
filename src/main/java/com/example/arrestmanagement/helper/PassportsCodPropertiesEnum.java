package com.example.arrestmanagement.helper;
public enum PassportsCodPropertiesEnum {

    FNS("FNS"),
    FSSP("FSSP"),
    FNS_PASSPORT("FNS_PASSPORT"),
    FNS_FOREIGN_PASSPORT("FNS_FOREIGN_PASSPORT"),
    FSSP_PASSPORT("FSSP_PASSPORT"),
    FSSP_FOREIGN_PASSPORT("FSSP_FOREIGN_PASSPORT"),
    CLIENT_DUL_TYPE_PASSPORT("CLIENT_DUL_TYPE_PASSPORT"),
    CLIENT_DUL_TYPE_FOREIGN_PASSPORT("CLIENT_DUL_TYPE_FOREIGN_PASSPORT"),
    ;


    private final String path;


    public String getPath() {
        return (PropertiesReader.getProperties(this.path));
    }

    PassportsCodPropertiesEnum(String path) {
        this.path = path;
    }

    private void ch() {

    }
}