package com.example.arrestmanagement.service;

import com.example.arrestmanagement.dto.ArrestDTO;
import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.dto.ArrestResponse;
import com.example.arrestmanagement.entity.Client;
import com.example.arrestmanagement.exception.handling.ArrestIncorrectException;
import com.example.arrestmanagement.exception.handling.NoSuchArrestException;
import com.example.arrestmanagement.helper.ErrorsPropertiesEnum;
import com.example.arrestmanagement.helper.OperationPropertiesEnum;
import com.example.arrestmanagement.service.impl.ClientServiceImpl;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor

public class MainService {

    @Autowired
    ArrestService arrestService;

    @Autowired
    ClientServiceImpl clientService;

    public ArrestResponse processRequest(ArrestRequest arrestRequest, BindingResult result) {

        ArrestResponse arrestResponse = new ArrestResponse();
        if (result.hasErrors()) {
            return getArrestResponseWithErrors(result, arrestResponse);
        }
        try {
            Client client = clientService.getClientFromRequest(arrestRequest);
            arrestService.createArrest(client, arrestRequest, arrestResponse);
            arrestService.editArrest(client, arrestRequest, arrestResponse);
            arrestService.canceledArrest(client, arrestRequest, arrestResponse);
            if (arrestResponse.getResultCode() == 0) {
                arrestResponse.setDecryption("success");
            }
        } catch (NoSuchArrestException | ArrestIncorrectException e) {
            arrestResponse.setResultCode(ErrorsPropertiesEnum.BUSINESS_ERROR.getCode());
            arrestResponse.setDecryption(e.getMessage());
            return arrestResponse;
        } catch (Exception e) {
            arrestResponse.setResultCode(ErrorsPropertiesEnum.TECHNICAL_ERROR.getCode());
            arrestResponse.setDecryption(e.getMessage());
            return arrestResponse;
        }
        return arrestResponse;
    }


    private ArrestResponse getArrestResponseWithErrors(BindingResult result, ArrestResponse arrestResponse) {
        arrestResponse.setResultCode(ErrorsPropertiesEnum.BUSINESS_ERROR.getCode());
        arrestResponse.setDecryption("Field: \"" +
                result.getFieldErrors().stream().map(FieldError::getRejectedValue).collect(Collectors.toList())
                + "\" " +
                result.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList())
                + " \n " + result.getTarget());
        return arrestResponse;
    }
}
