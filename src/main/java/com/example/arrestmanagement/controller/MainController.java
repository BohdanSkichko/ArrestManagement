package com.example.arrestmanagement.controller;

import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.entity.Arrest;
import com.example.arrestmanagement.service.ArrestService;
import com.example.arrestmanagement.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {
    @Autowired
    private final ArrestService arrestService;

    @Autowired

    private final ClientService clientService;

//    @Autowired
//    private ModelMapper modelMapper;


    public MainController(ArrestService arrestService, ClientService clientService) {
        this.arrestService = arrestService;
        this.clientService = clientService;
    }

    @PostMapping("/managerrest")
    public Arrest save(@RequestBody ArrestRequest request) {
       return arrestService.save(request);
    }


}
