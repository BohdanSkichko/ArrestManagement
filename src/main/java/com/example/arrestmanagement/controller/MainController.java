package com.example.arrestmanagement.controller;


import com.example.arrestmanagement.dto.ArrestDTO;
import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.dto.ArrestResponse;
import com.example.arrestmanagement.entity.Arrest;
import com.example.arrestmanagement.entity.Client;
import com.example.arrestmanagement.service.ArrestService;
import com.example.arrestmanagement.service.ClientService;
import com.example.arrestmanagement.validator.DUL_Validator.DUL;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/api")
public class MainController {

    private final int BUSINESS_ERROR = 3;
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
        ArrestResponse arrestResponse = new ArrestResponse();

        /*check Ident_Doc (if data incorrect -> throw NewRunTimeException,
         need change to business logic?) and get client from request*/
        Client client = getClient(request);

        /*get arrest from request*/
        ArrestDTO arrestDTO = request.getArrestDTO();
        Arrest arrest = getArrest(request, arrestDTO);

        /*check client is present? (unique Name, Surname, DUL type and Serial Number)*/
        Optional<Client> checkClient = findClient(client);

        if (!checkClient.isPresent()) {
            clientService.saveClient(client);
        }

        /*find client after save and work with this client*/
        Optional<Client> findClient = findClient(client);

        /* if operation == 1   find the client's arrest(with docNum), and add arrest to client.
        * if arrest present -> error business logic and message*/
        ArrestResponse operationOne = operationOne(arrestResponse, arrestDTO, arrest, findClient);
        if (operationOne != null) return operationOne;

        /* find arrest from client and RefDocNum*/
        Optional<Arrest> findArrest = arrestService.findByClientAndByDocNm(findClient.get(), arrestDTO.getRefDocNum());


        /*       if operation == 2, getArrest and update Arrest,
        * if arrest not found(refDocNum -> error business logic and message)  */
        ArrestResponse operationTwo = operationTwo(arrestResponse, arrestDTO, findArrest);
        if (operationTwo != null) return operationTwo;


        /*if operation ==3, getArrest and Canceled
        * if arrest not found(refDocNum -> error business logic and message) */
        ArrestResponse operationThree = operationThree(arrestResponse, arrestDTO, findArrest);
        if (operationThree != null) return operationThree;


        if (arrestResponse.getResultCode() == 0) {
            arrestResponse.setDecryption("success");
        }
        return arrestResponse;

    }

    @NotNull
    private Client getClient(ArrestRequest request) {
        Client client = new Client();
        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastname());
        DUL dul = new DUL();
        dul.check(client, request);
        return client;
    }

    @Nullable
    private ArrestResponse operationThree(ArrestResponse arrestResponse, ArrestDTO arrestDTO, Optional<Arrest> findArrest) {
        if (arrestDTO.getOperation() == 3) {
            if (!findArrest.isPresent()) {
                arrestResponse.setResultCode(BUSINESS_ERROR);
                arrestResponse.setDecryption("not find Arrest");
                return arrestResponse;
            }
            Arrest clientsArrest = findArrest.get();
            clientsArrest.setStatus(Arrest.Status.CANCELED);
            arrestService.updateArrest(clientsArrest);
            arrestResponse.setId(clientsArrest.getId());
        }
        return null;
    }

    @Nullable
    private ArrestResponse operationTwo(ArrestResponse arrestResponse, ArrestDTO arrestDTO, Optional<Arrest> findArrest) {
        if (arrestDTO.getOperation() == 2) {

            if (!findArrest.isPresent()) {
                arrestResponse.setResultCode(BUSINESS_ERROR);
                arrestResponse.setDecryption("not find Arrest");
                return arrestResponse;
            }
            Arrest arrestClient = findArrest.get();
            arrestClient.setAmount(arrestDTO.getAmount());
            arrestClient.setPurpose(arrestDTO.getPurpose());
            if (arrestClient.getAmount() > 0) {
                arrestClient.setStatus(Arrest.Status.ACTING);
            } else arrestClient.setStatus(Arrest.Status.CANCELED);
            arrestService.updateArrest(arrestClient);
            arrestResponse.setId(findArrest.get().getId());
        }
        return null;
    }

    @Nullable
    private ArrestResponse operationOne(ArrestResponse arrestResponse, ArrestDTO arrestDTO, Arrest arrest, Optional<Client> findClient) {
        if (arrestDTO.getOperation() == 1) {
            Optional<Arrest> findArrest = arrestService.findByClientAndByDocNm(findClient.get(), arrest.getDocNum());
            if (!findArrest.isPresent()) {
                arrest.setClient(findClient.get());
                findClient.get().addArrestToClient(arrest);
                arrestService.saveArrest(arrest);
                arrestResponse.setId(arrest.getId());
            } else {
                arrestResponse.setResultCode(BUSINESS_ERROR);
                arrestResponse.setDecryption("this arrest is already present");
                return arrestResponse;
            }
            /*       if operation == 1 create new Arrest and add to Client */
        }
        return null;
    }

    private Optional<Client> findClient(Client client) {
        return clientService.findClientByFirstNameAndLastNameAndDulTypeAndNumSeries(client.getFirstName(), client.getLastName(),
                client.getDulType(), client.getNumSeries());
    }

    @NotNull
    private Arrest getArrest(ArrestRequest request, ArrestDTO arrestDTO) {
        Arrest arrest = new Arrest();
        arrest.setDocDate(arrestDTO.getDocDate());
        arrest.setDocNum(arrestDTO.getDocNum());
        arrest.setPurpose(arrestDTO.getPurpose());
        arrest.setAmount(arrestDTO.getAmount());
        arrest.setOrganCode(request.getOrganCode());
        return arrest;
    }
}
