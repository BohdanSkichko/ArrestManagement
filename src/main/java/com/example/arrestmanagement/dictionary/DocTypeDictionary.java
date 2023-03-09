package com.example.arrestmanagement.dictionary;


import com.example.arrestmanagement.exception.ArrestIncorrectException;
import com.example.arrestmanagement.parameter.InnerIdentDoc;
import com.example.arrestmanagement.parameter.OuterIdentDoc;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.arrestmanagement.dictionary.ArrestOrganCodeEnum.*;
import static com.example.arrestmanagement.dictionary.InnerDocType.*;

@AllArgsConstructor
@Getter
public enum DocTypeDictionary {
    FNS_PASSPORT(FNS, PASSPORT, 21, "\\d{2} \\d{2} [0-9]{6}"),
    FNS_FOREIGN_PASSPORT(FNS, FOREIGN_PASSPORT, 22, "\\d{2} [0-9]{6}"),
    FSSP_PASSPORT(FSSP, PASSPORT, 70, "[0-9]{6}-\\d{4}"),
    FSSP_FOREIGN_PASSPORT(FSSP, FOREIGN_PASSPORT, 80, "[0-9]{6}\\.\\d{2}");

    private final ArrestOrganCodeEnum OrganCode;
    private final InnerDocType InnerDocType;
    private final Integer outerCode;
    private final String outerSerial;

    public static InnerIdentDoc getClientIdentDocFromArrest(OuterIdentDoc outerIdentDoc) {
        InnerIdentDoc innerIdentDoc = new InnerIdentDoc();
        for (DocTypeDictionary dictionaryElement : DocTypeDictionary.values()) {
            if (isDocTypeFits(outerIdentDoc, dictionaryElement)) {
                innerIdentDoc.setDulType(dictionaryElement.getInnerDocType().code);
                innerIdentDoc.setNumSeries(ClientNumberPassportTransformer.convertToClientFormat(outerIdentDoc.getNumSeries()));
                return innerIdentDoc;
            }
        }
        throw new ArrestIncorrectException("Type, DocNum or OrganCode Incorrect");
    }

    private static boolean isDocTypeFits(OuterIdentDoc outerIdentDoc, DocTypeDictionary dictionary) {
        return dictionary.getOrganCode().getCode() == (outerIdentDoc.getOrganCode()) &&
                dictionary.getOuterCode() == outerIdentDoc.getType() &&
                outerIdentDoc.getNumSeries().matches(dictionary.getOuterSerial());
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