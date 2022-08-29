package com.example.arrestmanagement.service;

import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.dto.ArrestResponse;
import com.example.arrestmanagement.entity.Client;
import com.example.arrestmanagement.exception.handling.ArrestIncorrectException;
import com.example.arrestmanagement.exception.handling.NoSuchArrestException;
import com.example.arrestmanagement.helper.PropertiesEnum;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor

public class MainService {

    @Autowired
    private final ArrestService arrestService;

    @Autowired

    private final ClientService clientService;

    public ArrestResponse putRequest(ArrestRequest arrestRequest, BindingResult result) {

        ArrestResponse arrestResponse = new ArrestResponse();
        if (result.hasErrors()) {
            arrestResponse.setResultCode((Integer.parseInt(PropertiesEnum.BUSINESS_ERROR.getPath())));
            arrestResponse.setDecryption( "Field: \"" +  Objects.requireNonNull(result.getFieldError()).getRejectedValue()
                    +  "\" " + result.getFieldError().getDefaultMessage()  + " \n " + result.getTarget());
            return arrestResponse;
        }
        try {
            Client client = clientService.getClient(arrestRequest);

            arrestService.createArrest(client, arrestRequest, arrestResponse);
            arrestService.editArrest(client, arrestRequest, arrestResponse);
            arrestService.canceledArrest(client, arrestRequest, arrestResponse);

            if (arrestResponse.getResultCode() == 0) {
                arrestResponse.setDecryption("success");
            }

        } catch (NoSuchArrestException | ArrestIncorrectException e) {
            arrestResponse.setResultCode(Integer.parseInt(PropertiesEnum.BUSINESS_ERROR.getPath()));
            arrestResponse.setDecryption(e.getMessage());
            return arrestResponse;
        } catch (Exception e) {
            arrestResponse.setResultCode(Integer.parseInt(PropertiesEnum.TECHNICAL_ERROR.getPath()));
            arrestResponse.setDecryption(e.getMessage());
            return arrestResponse;
        }
        return arrestResponse;
    }
}
