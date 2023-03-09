package com.example.arrestmanagement.dictionary;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArrestFNSPassportCodeEnum {
    PASSPORT(21),
    FOREIGN_PASSPORT(22);


    private final int code;

    public static boolean isCorrectCode(int someCode) {
        for (ArrestFNSPassportCodeEnum value : ArrestFNSPassportCodeEnum.values()) {
            if (value.getCode() == (someCode))
                return true;
        }
        return false;
    }
}

