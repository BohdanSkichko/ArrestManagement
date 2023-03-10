package com.example.arrestmanagement.service.impl;

import com.example.arrestmanagement.dto.ArrestDto;
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


    public void updateArrest(Arrest arrest) {
        arrestRepository.save(arrest);
    }


    public void saveArrest(Arrest arrest) {
        arrestRepository.save(arrest);
    }


    public Optional<Arrest> getArrestFromRequest(ArrestRequest arrestRequest) {
        ArrestDto arrestDTO = arrestRequest.getArrestDTO();
        Arrest arrest = new Arrest();
        arrest.setDocDate(arrestDTO.getDocDate());
        arrest.setDocNum(arrestDTO.getDocNum());
        arrest.setPurpose(arrestDTO.getPurpose());
        arrest.setAmount(arrestDTO.getAmount());
        arrest.setOrganCode(arrestRequest.getOrganCode());
        return Optional.of(arrest);
    }


    public Optional<Arrest> findByClientAndByDocNum(Client client, ArrestRequest arrestRequest) {
        ArrestDto arrestDTO = arrestRequest.getArrestDTO();
        String docNum = arrestDTO.getDocNum();
        return arrestRepository.findArrestByClientAndDocNum(client, docNum);
    }


    public Optional<Arrest> findByClientAndByRefDocNum(Client client, ArrestRequest arrestRequest) {
        ArrestDto arrestDTO = arrestRequest.getArrestDTO();
        String refDocNum = arrestDTO.getRefDocNum();
        return arrestRepository.findArrestByClientAndDocNum(client, refDocNum);
    }


    @Override
    public void editArrest(Client client, ArrestRequest arrestRequest, ArrestResponse arrestResponse) {
        ArrestDto arrestDTO = arrestRequest.getArrestDTO();
        if (arrestDTO.getOperation() != OperationPropertiesEnum.EDIT_ARREST.getCode()) {
            return;
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

    }

    @Override
    public void cancelArrest(Client client, ArrestRequest arrestRequest, ArrestResponse arrestResponse) {
        ArrestDto arrestDTO = arrestRequest.getArrestDTO();
        if (arrestDTO.getOperation() != OperationPropertiesEnum.CANCELED_ARREST.getCode()) {
            return;
        }
        Optional<Arrest> clientsArrest = findByClientAndByRefDocNum(client, arrestRequest);
        if (!clientsArrest.isPresent()) {
            throw new NoSuchArrestException("Arrest not found");
        }

        Arrest arrest = clientsArrest.get();
        arrest.setStatus(Arrest.Status.CANCELED);
        updateArrest(arrest);
        arrestResponse.setId(arrest.getId());
    }

    @Override
    public void createArrest(Client client, ArrestRequest arrestRequest, ArrestResponse arrestResponse) {
        ArrestDto arrestDTO = arrestRequest.getArrestDTO();
        if (arrestDTO.getOperation() != OperationPropertiesEnum.FIRST_OPERATION.getCode()) {
            return;
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
    }
}
