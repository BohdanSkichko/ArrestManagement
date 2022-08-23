package com.example.arrestmanagement.validator.DUL_Validator;

import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.dto.IdentDocDTO;
import com.example.arrestmanagement.entity.Client;
import com.example.arrestmanagement.validator.ident_doc_validator.fns.FNSForeignPassport;
import com.example.arrestmanagement.validator.ident_doc_validator.fns.FNSPassport;
import com.example.arrestmanagement.validator.ident_doc_validator.fssp.FSSPForeignPassport;
import com.example.arrestmanagement.validator.ident_doc_validator.fssp.FSSPPassport;
import lombok.*;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DUL {
    private final int FNS = 39;
    private final int FSSP = 17;
    private final int FNS_PASSPORT = 21;
    private final int FNS_FOREIGN_PASSPORT = 22;

    private final int FSSP_PASSPORT = 70;
    private final int FSSP_FOREIGN_PASSPORT = 80;
    private final int CLIENT_DUL_TYPE_PASSPORT = 1;
    private final int CLIENT_DUL_TYPE_FOREIGN_PASSPORT = 2;

    public Client check(Client client, ArrestRequest arrestRequest) {

            IdentDocDTO identDocDT0 = arrestRequest.getIdentDocDTO();
            String numSeries = identDocDT0.getNumberSeries();
            if (arrestRequest.getOrganCode() == FNS) {
                if (identDocDT0.getType() == FNS_PASSPORT) {
                    FNSPassport fnsPassport = new FNSPassport();
                    client.setNumSeries(fnsPassport.convertToClientFormat(numSeries));
                    client.setDulType(CLIENT_DUL_TYPE_PASSPORT);
                    return client;
                }
                if (identDocDT0.getType() == FNS_FOREIGN_PASSPORT) {
                    identDocDT0.setNumberSeries(new FNSForeignPassport().convertToClientFormat(numSeries));
                    identDocDT0.setType(CLIENT_DUL_TYPE_FOREIGN_PASSPORT);
                    return client;
                } else throw new RuntimeException("incorrect identDoc type");
            }
            if (arrestRequest.getOrganCode() == FSSP) {
                if (identDocDT0.getType() == FSSP_FOREIGN_PASSPORT) {
                    FSSPForeignPassport fsspForeignPassport = new FSSPForeignPassport();
                    client.setNumSeries(fsspForeignPassport.convertToClientFormat(numSeries));
                    client.setDulType(CLIENT_DUL_TYPE_FOREIGN_PASSPORT);
                    return client;
                }
                if (identDocDT0.getType() == FSSP_PASSPORT) {
                    client.setNumSeries(new FSSPPassport().convertToClientFormat(numSeries));
                    client.setDulType(CLIENT_DUL_TYPE_PASSPORT);
                    return client;
                } else throw new RuntimeException("incorrect identDoc type");
            } else throw new RuntimeException("incorrect organCode");

    }
}