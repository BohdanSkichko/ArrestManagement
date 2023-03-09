package com.example.arrestmanagement.dictionary;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArrestFSSPPassportCodeEnum {

    PASSPORT(70),
    FOREIGN_PASSPORT(80);
    private final int code;

    public static boolean isCorrectCode(int someCode) {
        for (ArrestFSSPPassportCodeEnum value : ArrestFSSPPassportCodeEnum.values()) {
            if (value.getCode() == (someCode))
                return true;
        }
        return false;
    }

}
