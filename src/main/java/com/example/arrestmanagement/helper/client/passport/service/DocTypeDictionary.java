package com.example.arrestmanagement.helper.client.passport.service;


import com.example.arrestmanagement.exception.handling.ArrestIncorrectException;
import com.example.arrestmanagement.helper.ArrestOrganCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.arrestmanagement.helper.ArrestOrganCodeEnum.*;
import static com.example.arrestmanagement.helper.client.passport.service.InnerDocType.*;

@AllArgsConstructor
@Getter
public enum DocTypeDictionary {
    FNS_PASSPORT(FNS, PASSPORT, 21, "\\d{2} \\d{2} [0-9]{6}"),
    FNS_FOREIGN_PASSPORT(FNS, FOREIGN_PASSPORT, 22, "\\d{2} [0-9]{6}"),
    FSSP_PASSPORT(FSSP, PASSPORT, 70, "[0-9]{6}-\\d{4}"),
    FSSP_FOREIGN_PASSPORT(FSSP, FOREIGN_PASSPORT, 80, "[0-9]{6}\\.\\d{2}");

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
//                clientIdentDoc.setNumSeries(ClientPassportTransformer.convertToClientFormat(arrestDocType.getNumSeries()));
                clientIdentDoc.setNumSeries(ClientNumberPassportTransformer.convertToClientFormat(arrestDocType.getNumSeries()));
                return clientIdentDoc;
            }
        }
        throw new ArrestIncorrectException("Incorrect information between Type, DocNum or OrganCode");
    }


    static class ClientPassportTransformer {
        static String convertToClientFormat(String format) {
            String justLettersAndDigits = format.replaceAll("[\\.\\-\\ ]", "");
            StringBuilder clientPassport = new StringBuilder();
            List<Character> characters = justLettersAndDigits.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
            for (Character character : characters) {
                if (Character.isAlphabetic(character)) {
                    clientPassport.append(character);
                }
            }
            int numberDigit = 0;
            for (Character character : characters) {
                if (Character.isDigit(character) && numberDigit % 2 != 0) {
                    clientPassport.append(character);
                    numberDigit++;
                } else if (Character.isDigit(character)) {
                    clientPassport.append(" ");
                    clientPassport.append(character);
                    numberDigit++;
                }
            }

            return clientPassport.toString();
        }
    }

    static class ClientNumberPassportTransformer {
        static String convertToClientFormat(String numSeries) {
            String s = numSeries.replaceAll("[\\.\\-]", " ");
            String passportSerial = s.replaceAll("[0-9]{6}","").trim();
            String passportNumber = s.replace(passportSerial,"").trim();
            if (passportSerial.length() == 4) {
                StringBuilder passportSer = new StringBuilder(passportSerial);
                passportSer.insert(2, " ");
                return passportNumber + " " + passportSer;
            }
         return passportNumber + " " + passportSerial;
        }
    }
}