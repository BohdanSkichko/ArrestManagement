package com.example.arrestmanagement.validator;

import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.dto.IdentDocDTO;
import com.example.arrestmanagement.validator.fssp.FSSPForeignPassport;
import com.example.arrestmanagement.validator.fssp.FSSPPassport;
import com.example.arrestmanagement.validator.fns.FNSForeignPassport;
import com.example.arrestmanagement.validator.fns.FNSPassport;
import lombok.*;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ClientsIdentDoc {
    private final int FNS = 39;
    private final int FSSP = 17;
    private final int FNS_PASSPORT = 21;
    private final int FNS_FOREIGN_PASSPORT = 22;

    private final int FSSP_PASSPORT = 70;
    private final int FSSP_FOREIGN_PASSPORT = 80;
    private final int CLIENT_DUL_TYPE_PASSPORT = 1;
    private final int CLIENT_DUL_TYPE_FOREIGN_PASSPORT = 2;

    public IdentDocDTO createClientsFormat(ArrestRequest arrestRequest) {

        IdentDocDTO identDocDT0 = arrestRequest.getIdentDocDTO();
        String numSeries = identDocDT0.getNumberSeries();
        if (arrestRequest.getOrganCode() == FNS) {
            if (identDocDT0.getType() == FNS_PASSPORT) {
                identDocDT0.setNumberSeries(new FNSPassport().convertToClientFormat(numSeries));
                identDocDT0.setType(CLIENT_DUL_TYPE_PASSPORT);
                return identDocDT0;
            }
            if (identDocDT0.getType() == FNS_FOREIGN_PASSPORT) {
                identDocDT0.setNumberSeries(new FNSForeignPassport().convertToClientFormat(numSeries));
                identDocDT0.setType(CLIENT_DUL_TYPE_FOREIGN_PASSPORT);
                return identDocDT0;
            } else throw new IllegalArgumentException("incorrect identDoc type");
        }
        if (arrestRequest.getOrganCode() == FSSP) {
            if (identDocDT0.getType() == FSSP_FOREIGN_PASSPORT) {
                identDocDT0.setNumberSeries(new FSSPForeignPassport().convertToClientFormat(numSeries));
                identDocDT0.setType(CLIENT_DUL_TYPE_FOREIGN_PASSPORT);
                return identDocDT0;
            }
            if (identDocDT0.getType() == FSSP_PASSPORT) {
                identDocDT0.setNumberSeries(new FSSPPassport().convertToClientFormat(numSeries));
                identDocDT0.setType(CLIENT_DUL_TYPE_PASSPORT);
                return identDocDT0;
            } else throw new IllegalArgumentException("incorrect identDoc type");
        } else throw new IllegalArgumentException("incorrect organCode");

    }
}