package com.example.arrestmanagement.helper;

public enum OperationPropertiesEnum {
    FIRST_OPERATION("FIRST_OPERATION"),
    EDIT_ARREST("EDIT_ARREST"),
    CANCELED_ARREST("CANCELED_ARREST"),
    ;
    private final String path;

    OperationPropertiesEnum(String path) {
        this.path = path;
    }


    public String getPath() {
        return (PropertiesReader.getProperties(this.path));
    }


}
