package com.example.arrestmanagement.controller;


import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.dto.ArrestResponse;
import com.example.arrestmanagement.service.MainService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Validated
public class MainController {

    @Autowired
    private final MainService mainService;

    @PostMapping("/managerarrest")
    public ArrestResponse putRequest(@Validated @RequestBody ArrestRequest request, BindingResult result) {
        return mainService.processRequest(request, result);
    }
}
