package com.example.arrestmanagement.controller;


import com.example.arrestmanagement.dto.ArrestDTO;
import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.dto.ArrestResponse;
import com.example.arrestmanagement.dto.IdentDocDTO;
import com.example.arrestmanagement.entity.Arrest;
import com.example.arrestmanagement.entity.Client;
import com.example.arrestmanagement.ident_doc_validator.fns.FNSForeignPassport;
import com.example.arrestmanagement.ident_doc_validator.fns.FNSPassport;
import com.example.arrestmanagement.ident_doc_validator.fssp.FSSPPassport;
import com.example.arrestmanagement.ident_doc_validator.fssp.FSSPForeignPassport;
import com.example.arrestmanagement.service.ArrestService;
import com.example.arrestmanagement.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/api")
public class MainController {

    private final int FNS = 39;
    private final int FSSP = 17;

    private final int FNS_PASSPORT = 21;
    private final int FNS_FOREIGN_PASSPORT = 22;

    private final int FSSP_PASSPORT = 70;
    private final int FSSP_FOREIGN_PASSPORT = 80;
    private final int CLIENT_DUL_TYPE_PASSPORT = 1;
    private final int CLIENT_DUL_TYPE_FOREIGN_PASSPORT = 2;
    @Autowired
    private final ArrestService arrestService;

    @Autowired

    private final ClientService clientService;

    public MainController(ArrestService arrestService, ClientService clientService) {
        this.arrestService = arrestService;
        this.clientService = clientService;
    }

    @PostMapping("/managerarrest")
    public ArrestResponse putRequest(@Valid @RequestBody ArrestRequest request
    ) {
        Client client = new Client();
        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastname());

        IdentDocDTO identDocDTO = request.getIdentDocDTO();

        String numSeries = identDocDTO.getNumberSeries();

        if (request.getOrganCode() == FSSP) {
            if (identDocDTO.getType() == FSSP_PASSPORT) {
                client.setDulType(CLIENT_DUL_TYPE_PASSPORT);

                FSSPPassport fsspPassport = new FSSPPassport();
                fsspPassport.setFormat(numSeries);

                client.setNumSeries(fsspPassport.convertToClientFormat());

            } else if (identDocDTO.getType() == FSSP_FOREIGN_PASSPORT) {
                client.setDulType(CLIENT_DUL_TYPE_FOREIGN_PASSPORT);

                FSSPForeignPassport fsspForeignPassport = new FSSPForeignPassport();
                fsspForeignPassport.setFormat(numSeries);


                client.setNumSeries(fsspForeignPassport.convertToClientFormat());


            } else throw new RuntimeException("incorrect IdentDoc type");

        } else if (request.getOrganCode() == FNS) {
            if (identDocDTO.getType() == FNS_PASSPORT) {
                client.setDulType(CLIENT_DUL_TYPE_PASSPORT);

                FNSPassport fnsPassport = new FNSPassport();
                fnsPassport.setFormat(numSeries);

                client.setNumSeries(fnsPassport.convertToClientFormat());

            } else if (identDocDTO.getType() == FNS_FOREIGN_PASSPORT) {
                client.setDulType(CLIENT_DUL_TYPE_FOREIGN_PASSPORT);

                FNSForeignPassport fnsForeignPassport = new FNSForeignPassport();
                fnsForeignPassport.setFormat(numSeries);

                client.setNumSeries(fnsForeignPassport.convertToClientFormat());

            } else throw new RuntimeException("incorrect Ident_Doc type");
        } else throw new RuntimeException("incorrect Orane code");


        ArrestDTO arrestDTO = request.getArrestDTO();

        Arrest arrest = new Arrest();
        arrest.setDocDate(arrestDTO.getDocDate());
        arrest.setDocNum(arrestDTO.getDocNum());
        arrest.setPurpose(arrestDTO.getPurpose());
        arrest.setAmount(arrestDTO.getAmount());
        arrest.setOrganCode(request.getOrganCode());
        if (arrestDTO.getOperation() == 2) {
         /*      RefDocNum -> NNNNNN SS
         *       method change -> change arrest */

        } else if (arrestDTO.getOperation() == 3) {
            /*      RefDocNum -> NNNNNN SS
             *       method canceled -> canceled arrest */
        } else {
            arrestService.saveArrest(arrest);
        }
        Optional<Client> findClient =
                clientService.findClientByFirstNameAndLastNameAndDulTypeAndNumSeries(client.getFirstName(), client.getLastName(),
                        client.getDulType(), client.getNumSeries());

        if (findClient.isPresent()) {
            Client client1 = findClient.get();
            arrest.setClient(client1);
            client1.addArrestToClient(arrest);

            clientService.saveClient(client1);
        } else {
            arrest.setClient(client);
            client.addArrestToClient(arrest);
            clientService.saveClient(client);
        }


        ArrestResponse arrestResponse = new ArrestResponse();
        arrestResponse.setId(arrest.getId());

        return arrestResponse;

    }

    // method change arrest

//    method canceled arrest
}
