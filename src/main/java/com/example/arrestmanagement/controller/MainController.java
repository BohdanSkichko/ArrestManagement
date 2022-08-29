package com.example.arrestmanagement.controller;


import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.dto.ArrestResponse;
import com.example.arrestmanagement.service.MainService;
import lombok.AllArgsConstructor;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class MainController {


    MainService mainService;

    @PostMapping("/managerarrest")
    public ArrestResponse putRequest(@Valid @RequestBody ArrestRequest request, BindingResult result) {
            return mainService.putRequest(request, result);
    }
}
