package com.example.arrestmanagement.helper.client.passport.service;


import com.example.arrestmanagement.helper.ArrestOrganCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.example.arrestmanagement.helper.ArrestOrganCodeEnum.*;
import static com.example.arrestmanagement.helper.client.passport.service.InnerDocType.*;

@AllArgsConstructor
@Getter
public enum DocTypeDictionary {
    FNS_PASSPORT(FNS, PASSPORT, 21, "\\d{2} \\d{2} [A-Z]{6}"),
    FNS_FOREIGN_PASSPORT(FNS, FOREIGN_PASSPORT, 22, "\\d{2} [A-Z]{6}"),
    FSSP_PASSPORT(FSSP, PASSPORT, 70, "[A-Z]{6}-\\d{4}"),
    FSSP_FOREIGN_PASSPORT(FSSP, FOREIGN_PASSPORT, 80, "[A-Z]{6}\\.\\d{2}");

    private ArrestOrganCodeEnum OrganCode;
    private InnerDocType InnerDocType;
    private Integer outerCode;
    private String outerSerial;

    public static ClientIdentDoc getClientIdentDocFromArrest(ArrestDocType arrestDocType) {
        ClientIdentDoc clientIdentDoc = new ClientIdentDoc();
        for (DocTypeDictionary value : DocTypeDictionary.values()) {
            if (value.getOrganCode().getCode() == (arrestDocType.getOrganCode()) &&
                    value.getOuterCode() == arrestDocType.getType() &&
                    arrestDocType.getNumSeries().matches(value.getOuterSerial())
            ) {
                clientIdentDoc.setDulType(value.getInnerDocType().code);
                clientIdentDoc.setNumSeries(ClientPassportTransformer.convertToClientFormat(arrestDocType.getNumSeries()));
                return clientIdentDoc;
            }
        }
        throw new IllegalArgumentException("illegal information between Type, DocNum or OrganCode");
    }


    static class ClientPassportTransformer {
        static String convertToClientFormat(String format) {
            String justLettersAndDigits = format.replaceAll("\\.\\- ", "");
            StringBuilder stringBuilder = new StringBuilder();
            char[] chars = justLettersAndDigits.toCharArray();
            for (Character character : chars) {
                if (Character.isAlphabetic(character)) {
                    stringBuilder.append(character);
                }
            }
            stringBuilder.append(" ");
            for (Character character : chars) {
                if (Character.isDigit(character)) {
                    stringBuilder.append(character);
                }
            }
            return stringBuilder.toString();
        }
    }
}
