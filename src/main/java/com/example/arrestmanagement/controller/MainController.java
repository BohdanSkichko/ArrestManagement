package com.example.arrestmanagement.controller;


import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.dto.ArrestResponse;
import com.example.arrestmanagement.service.MainService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@AllArgsConstructor
@RequestMapping("/api")

public class MainController {

    @Autowired
    private final MainService mainService;

    @PostMapping("/managerarrest")
    @ApiOperation(value = "Получить сведения о пользователе",
            notes = "Получить сведения о пользователе в соответствии с идентификатором URL-адреса")
    @ApiImplicitParam(name = "id", value = "User ID", required = true,
            dataType = "Integer", paramType = "path")

    public ArrestResponse putRequest(@Validated @RequestBody ArrestRequest request, BindingResult result) {
        return mainService.processRequest(request, result);
    }
}
