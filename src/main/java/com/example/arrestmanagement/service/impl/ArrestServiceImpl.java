package com.example.arrestmanagement.service.impl;

import com.example.arrestmanagement.dto.ArrestDTO;
import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.dto.ArrestResponse;
import com.example.arrestmanagement.entity.Arrest;
import com.example.arrestmanagement.entity.Client;
import com.example.arrestmanagement.exception.handling.ArrestIncorrectException;
import com.example.arrestmanagement.exception.handling.NoSuchArrestException;
import com.example.arrestmanagement.helper.OperationPropertiesEnum;
import com.example.arrestmanagement.repository.ArrestRepository;
import com.example.arrestmanagement.repository.ClientRepository;
import com.example.arrestmanagement.service.ArrestService;
import com.example.arrestmanagement.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@AllArgsConstructor
@Service
public class ArrestServiceImpl implements ArrestService {
    @Autowired
    private final ArrestRepository arrestRepository;

    @Override
    public Arrest updateArrest(Arrest arrest) {
        return arrestRepository.save(arrest);
    }

    @Override
    public Arrest saveArrest(Arrest arrest) {
        return arrestRepository.save(arrest);
    }


    public Optional<Arrest> getArrestFromRequest(ArrestRequest arrestRequest) {
        ArrestDTO arrestDTO = arrestRequest.getArrestDTO();
        Arrest arrest = new Arrest();
        arrest.setDocDate(arrestDTO.getDocDate());
        arrest.setDocNum(arrestDTO.getDocNum());
        arrest.setPurpose(arrestDTO.getPurpose());
        arrest.setAmount(arrestDTO.getAmount());
        arrest.setOrganCode(arrestRequest.getOrganCode());
        return Optional.of(arrest);
    }


    @Override
    public Optional<Arrest> findByClientAndByDocNum(Client client, ArrestRequest arrestRequest) {
        ArrestDTO arrestDTO = arrestRequest.getArrestDTO();
        String docNum = arrestDTO.getDocNum();
        return arrestRepository.findArrestByClientAndDocNum(client, docNum);
    }

    public Optional<Arrest> findByClientAndByRefDocNum(Client client, ArrestRequest arrestRequest) {
        ArrestDTO arrestDTO = arrestRequest.getArrestDTO();
        String refDocNum = arrestDTO.getRefDocNum();
        return arrestRepository.findArrestByClientAndDocNum(client, refDocNum);
    }


    public ArrestResponse editArrest(Client client, ArrestRequest arrestRequest, ArrestResponse arrestResponse) {
        ArrestDTO arrestDTO = arrestRequest.getArrestDTO();
        if (arrestDTO.getOperation() != OperationPropertiesEnum.EDIT_ARREST.getCode()) {
            return arrestResponse;
        }
        Optional<Arrest> clientsArrest = findByClientAndByRefDocNum(client, arrestRequest);
        if (!clientsArrest.isPresent()) {
            throw new NoSuchArrestException("Arrest not found");
        } else {
            Arrest arrest = clientsArrest.get();
            arrest.setAmount(arrestDTO.getAmount());
            arrest.setPurpose(arrestDTO.getPurpose());
            if (arrest.getAmount() > 0) {
                arrest.setStatus(Arrest.Status.ACTING);
            } else {
                arrest.setStatus(Arrest.Status.CANCELED);
                saveArrest(arrest);
            }
            arrestResponse.setId(arrest.getId());
        }
        return arrestResponse;

    }

    public ArrestResponse canceledArrest(Client client, ArrestRequest arrestRequest, ArrestResponse arrestResponse) {
        ArrestDTO arrestDTO = arrestRequest.getArrestDTO();
        if (arrestDTO.getOperation() != OperationPropertiesEnum.CANCELED_ARREST.getCode()) {
            return arrestResponse;
        }
        Optional<Arrest> clientsArrest = findByClientAndByRefDocNum(client, arrestRequest);
        if (!clientsArrest.isPresent()) {
            throw new NoSuchArrestException("Arrest not found");
        }

        Arrest arrest = clientsArrest.get();
        arrest.setStatus(Arrest.Status.CANCELED);
        updateArrest(arrest);
        arrestResponse.setId(arrest.getId());
        return arrestResponse;
    }

    public ArrestResponse createArrest(Client client, ArrestRequest arrestRequest, ArrestResponse arrestResponse) {
        ArrestDTO arrestDTO = arrestRequest.getArrestDTO();
        if (arrestDTO.getOperation() != OperationPropertiesEnum.FIRST_OPERATION.getCode()) {
            return arrestResponse;
        }
        Optional<Arrest> findArrest = findByClientAndByDocNum(client, arrestRequest);
        if (findArrest.isPresent()) {
            throw new ArrestIncorrectException("this arrest is already present");
        }
        Arrest arrest = getArrestFromRequest(arrestRequest).get();
        arrest.setClient(client);
        client.addArrestToClient(arrest);
        saveArrest(arrest);
        arrestResponse.setId(arrest.getId());
        return arrestResponse;
    }
}
