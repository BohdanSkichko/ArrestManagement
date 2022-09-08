package com.example.arrestmanagement.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArrestOrganCodeEnum {
    FSSP(17),
    FNS(39);
    private final int code;

    public static boolean isCorrectCode(int someCode) {
        for (ArrestOrganCodeEnum value : ArrestOrganCodeEnum.values()) {
            if (value.getCode() == (someCode))
                return true;
        }
        return false;
    }
}

