package com.example.arrestmanagement.validation.handling;

import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.dto.IdentDocDTO;
import com.example.arrestmanagement.exception.handling.ArrestIncorrectException;
import com.example.arrestmanagement.validation.handling.fssp.FSSPForeignPassport;
import com.example.arrestmanagement.validation.handling.fssp.FSSPPassport;
import com.example.arrestmanagement.validation.handling.fns.FNSForeignPassport;
import com.example.arrestmanagement.validation.handling.fns.FNSPassport;
import com.example.arrestmanagement.helper.PassportsCodPropertiesEnum;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@Component
@AllArgsConstructor
public class ClientsIdentDocTransformer {
    @Autowired
    private final FNSForeignPassport fnsForeignPassport;
    @Autowired
    private final FNSPassport fnsPassport;
    @Autowired
    private final FSSPPassport fsspPassport;
    @Autowired
    private final FSSPForeignPassport fsspForeignPassport;

    @Autowired
    private final ClientIdentDoc clientIdentDoc;


    public ClientIdentDoc createClientsFormat(ArrestRequest arrestRequest) {

        IdentDocDTO identDocDT0 = arrestRequest.getIdentDocDTO();
        String numSeries = identDocDT0.getNumberSeries();
        if (arrestRequest.getOrganCode() == Integer.parseInt(PassportsCodPropertiesEnum.FNS.getPath())) {
            return transformationIdentDocForFNS(identDocDT0, numSeries);
        }
        if (arrestRequest.getOrganCode() == Integer.parseInt(PassportsCodPropertiesEnum.FSSP.getPath())) {
            return transformationIdentDocForFSSP(identDocDT0, numSeries);
        } else throw new ArrestIncorrectException("incorrect organCode");
    }


    private ClientIdentDoc transformationIdentDocForFNS(IdentDocDTO identDocDT0, String numSeries) {
        if (identDocDT0.getType() == Integer.parseInt(PassportsCodPropertiesEnum.FNS_PASSPORT.getPath())) {
            clientIdentDoc.setNumSeries(fnsPassport.convertToClientFormat(numSeries));
            clientIdentDoc.setDulType(Integer.parseInt(PassportsCodPropertiesEnum.CLIENT_DUL_TYPE_PASSPORT.getPath()));
            return clientIdentDoc;
        }
        if (identDocDT0.getType() == Integer.parseInt(PassportsCodPropertiesEnum.FNS_FOREIGN_PASSPORT.getPath())) {
            clientIdentDoc.setNumSeries(fnsForeignPassport.convertToClientFormat(numSeries));
            clientIdentDoc.setDulType(Integer.parseInt(PassportsCodPropertiesEnum.CLIENT_DUL_TYPE_FOREIGN_PASSPORT.getPath()));
            return clientIdentDoc;
        } else throw new ArrestIncorrectException("incorrect identDoc type");
    }

    private ClientIdentDoc transformationIdentDocForFSSP(IdentDocDTO identDocDT0, String numSeries) {
        if (identDocDT0.getType() == Integer.parseInt(PassportsCodPropertiesEnum.FSSP_FOREIGN_PASSPORT.getPath())) {
            clientIdentDoc.setNumSeries(fsspForeignPassport.convertToClientFormat(numSeries));
            clientIdentDoc.setDulType(Integer.parseInt(PassportsCodPropertiesEnum.CLIENT_DUL_TYPE_FOREIGN_PASSPORT.getPath()));
            return clientIdentDoc;
        }
        if (identDocDT0.getType() == Integer.parseInt(PassportsCodPropertiesEnum.FSSP.getPath())) {
            clientIdentDoc.setNumSeries(fsspPassport.convertToClientFormat(numSeries));
            clientIdentDoc.setDulType(Integer.parseInt(PassportsCodPropertiesEnum.CLIENT_DUL_TYPE_PASSPORT.getPath()));
            return clientIdentDoc;
        } else throw new ArrestIncorrectException("incorrect identDoc type");
    }
}