package com.example.arrestmanagement.service.impl;

import com.example.arrestmanagement.dto.ArrestDTO;
import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.dto.ArrestResponse;
import com.example.arrestmanagement.entity.Arrest;
import com.example.arrestmanagement.entity.Client;
import com.example.arrestmanagement.exception.ArrestIncorrectException;
import com.example.arrestmanagement.exception.NoSuchArrestException;
import com.example.arrestmanagement.dictionary.OperationPropertiesEnum;
import com.example.arrestmanagement.repository.ArrestRepository;
import com.example.arrestmanagement.service.ArrestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@AllArgsConstructor
@Service
public class ArrestServiceImpl implements ArrestService {

    private final ArrestRepository arrestRepository;

    @Override
    public Arrest updateArrest(Arrest arrest) {
        return arrestRepository.save(arrest);
    }

    @Override
    public Arrest saveArrest(Arrest arrest) {
        return arrestRepository.save(arrest);
    }

    @Override
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

    @Override
    public Optional<Arrest> findByClientAndByRefDocNum(Client client, ArrestRequest arrestRequest) {
        ArrestDTO arrestDTO = arrestRequest.getArrestDTO();
        String refDocNum = arrestDTO.getRefDocNum();
        return arrestRepository.findArrestByClientAndDocNum(client, refDocNum);
    }


    @Override
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

    @Override
    public ArrestResponse cancelArrest(Client client, ArrestRequest arrestRequest, ArrestResponse arrestResponse) {
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

    @Override
    public ArrestResponse createArrest(Client client, ArrestRequest arrestRequest, ArrestResponse arrestResponse) {
        ArrestDTO arrestDTO = arrestRequest.getArrestDTO();
        if (arrestDTO.getOperation() != OperationPropertiesEnum.FIRST_OPERATION.getCode()) {
            return arrestResponse;
        }
        Optional<Arrest> findArrest = findByClientAndByDocNum(client, arrestRequest);
        if (findArrest.isPresent()) {
            throw new ArrestIncorrectException("This arrest is already present");
        }
        Arrest arrest = getArrestFromRequest(arrestRequest).get();
        arrest.setClient(client);
        client.addArrestToClient(arrest);
        saveArrest(arrest);
        arrestResponse.setId(arrest.getId());
        return arrestResponse;
    }
}
