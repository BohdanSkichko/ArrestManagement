package com.example.arrestmanagement.controller;


import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.dto.ArrestResponse;
import com.example.arrestmanagement.service.MainService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/api")

public class ArrestController {

    @Autowired
    private final MainService mainService;

    @PostMapping("/managearrest")
    public ArrestResponse putRequest(@Validated @RequestBody ArrestRequest request, BindingResult result) {
        return mainService.processRequest(request, result);
    }
}
