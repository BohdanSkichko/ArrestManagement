package com.example.arrestmanagement.controller;


import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.dto.ArrestResponse;
import com.example.arrestmanagement.entity.Client;
import com.example.arrestmanagement.service.ArrestService;
import com.example.arrestmanagement.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@Validated
@Transactional
@RequestMapping("/api")
public class MainController {

    private final int TECHNICAL_ERROR = 5;
    @Autowired
    private final ArrestService arrestService;

    @Autowired

    private final ClientService clientService;

    public MainController(ArrestService arrestService, ClientService clientService) {
        this.arrestService = arrestService;
        this.clientService = clientService;
    }

    @PostMapping("/managerarrest")
    public ArrestResponse putRequest(@Validated @RequestBody ArrestRequest request) {

        ArrestResponse arrestResponse = new ArrestResponse();
        try {
            Client client = clientService.getClient(request);

            arrestService.createArrest(client, request, arrestResponse);
            arrestService.editArrest(client, request, arrestResponse);
            arrestService.canceledArrest(client, request, arrestResponse);


            if (arrestResponse.getResultCode() == 0) {
                arrestResponse.setDecryption("success");
            }
        } catch (Exception e) {
            arrestResponse.setResultCode(TECHNICAL_ERROR);
            arrestResponse.setDecryption(e.getMessage());
            return arrestResponse;
        }
        return arrestResponse;
    }

}
