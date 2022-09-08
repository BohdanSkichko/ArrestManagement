package com.example.arrestmanagement.helper.client.passport.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum InnerDocType {
    PASSPORT(1),
    FOREIGN_PASSPORT(2),
    ;
    public final int code;
}
