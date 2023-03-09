package com.example.arrestmanagement.dictionary;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OperationPropertiesEnum {
    FIRST_OPERATION(1),
    EDIT_ARREST(2),
    CANCELED_ARREST(3),
    ;
    private final int code;


}
