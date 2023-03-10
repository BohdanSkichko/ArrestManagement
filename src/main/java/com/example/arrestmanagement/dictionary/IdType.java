package com.example.arrestmanagement.dictionary;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IdType {
    PASSPORT(1),
    FOREIGN_PASSPORT(2),
    ;
    public final int code;
}
