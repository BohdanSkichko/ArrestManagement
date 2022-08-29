package com.example.arrestmanagement.validation.handling;

import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.dto.IdentDocDTO;
import com.example.arrestmanagement.exception.handling.ArrestIncorrectException;
import com.example.arrestmanagement.validation.handling.fssp.FSSPForeignPassport;
import com.example.arrestmanagement.validation.handling.fssp.FSSPPassport;
import com.example.arrestmanagement.validation.handling.fns.FNSForeignPassport;
import com.example.arrestmanagement.validation.handling.fns.FNSPassport;
import com.example.arrestmanagement.helper.PropertiesEnum;
import lombok.*;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ClientsIdentDoc {
    public IdentDocDTO createClientsFormat(ArrestRequest arrestRequest) {

        IdentDocDTO identDocDT0 = arrestRequest.getIdentDocDTO();
        String numSeries = identDocDT0.getNumberSeries();
        if (arrestRequest.getOrganCode() == Integer.parseInt(PropertiesEnum.FNS.getPath())) {
            if (identDocDT0.getType() == Integer.parseInt(PropertiesEnum.FNS_PASSPORT.getPath())) {
                identDocDT0.setNumberSeries(new FNSPassport().convertToClientFormat(numSeries));
                identDocDT0.setType(Integer.parseInt(PropertiesEnum.CLIENT_DUL_TYPE_PASSPORT.getPath()));
                return identDocDT0;
            }
            if (identDocDT0.getType() == Integer.parseInt(PropertiesEnum.FNS_FOREIGN_PASSPORT.getPath())) {
                identDocDT0.setNumberSeries(new FNSForeignPassport().convertToClientFormat(numSeries));
                identDocDT0.setType(Integer.parseInt(PropertiesEnum.CLIENT_DUL_TYPE_FOREIGN_PASSPORT.getPath()));
                return identDocDT0;
            } else throw new ArrestIncorrectException("incorrect identDoc type");
        }
        if (arrestRequest.getOrganCode() == Integer.parseInt(PropertiesEnum.FSSP.getPath())) {
            if (identDocDT0.getType() ==Integer.parseInt(PropertiesEnum.FSSP_FOREIGN_PASSPORT.getPath())) {
                identDocDT0.setNumberSeries(new FSSPForeignPassport().convertToClientFormat(numSeries));
                identDocDT0.setType(Integer.parseInt(PropertiesEnum.CLIENT_DUL_TYPE_FOREIGN_PASSPORT.getPath()));
                return identDocDT0;
            }
            if (identDocDT0.getType() == Integer.parseInt(PropertiesEnum.FSSP.getPath())) {
                identDocDT0.setNumberSeries(new FSSPPassport().convertToClientFormat(numSeries));
                identDocDT0.setType(Integer.parseInt(PropertiesEnum.CLIENT_DUL_TYPE_PASSPORT.getPath()));
                return identDocDT0;
            } else throw new ArrestIncorrectException("incorrect identDoc type");
        } else throw new ArrestIncorrectException("incorrect organCode");

    }
}