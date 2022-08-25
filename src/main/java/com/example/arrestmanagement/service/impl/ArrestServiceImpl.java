package com.example.arrestmanagement.service.impl;

import com.example.arrestmanagement.dto.ArrestDTO;
import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.dto.ArrestResponse;
import com.example.arrestmanagement.entity.Arrest;
import com.example.arrestmanagement.entity.Client;
import com.example.arrestmanagement.repository.ArrestRepository;
import com.example.arrestmanagement.service.ArrestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ArrestServiceImpl implements ArrestService {

    private final int BUSINESS_ERROR = 3;

    private final int FIRST_OPERATION = 1;
    private final int EDIT_ARREST = 2;
    private final int CANCELED_ARREST = 3;

    @Autowired
    private final ArrestRepository arrestRepository;

    public ArrestServiceImpl(ArrestRepository arrestRepository) {
        this.arrestRepository = arrestRepository;
    }


    @Override
    public Arrest updateArrest(Arrest arrest) {
        return arrestRepository.save(arrest);
    }

    @Override
    public Arrest saveArrest(Arrest arrest) {

        return arrestRepository.save(arrest);
    }


    public Optional<Arrest> getArrest(ArrestRequest arrestRequest) {
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
        if (arrestDTO.getOperation() == EDIT_ARREST) {
            Optional<Arrest> clientsArrest = findByClientAndByRefDocNum(client, arrestRequest);
            if (clientsArrest.isPresent()) {
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
            } else {
                arrestResponse.setResultCode(BUSINESS_ERROR);
                arrestResponse.setDecryption("not find Arrest");
            }
        }
        return arrestResponse;

    }

    public ArrestResponse canceledArrest(Client client, ArrestRequest arrestRequest, ArrestResponse arrestResponse) {
        ArrestDTO arrestDTO = arrestRequest.getArrestDTO();
        if (arrestDTO.getOperation() == CANCELED_ARREST) {
            Optional<Arrest> clientsArrest = findByClientAndByRefDocNum(client, arrestRequest);
            if (clientsArrest.isPresent()) {
                Arrest arrest = clientsArrest.get();
                arrest.setStatus(Arrest.Status.CANCELED);
                updateArrest(arrest);
                arrestResponse.setId(arrest.getId());
            } else {
                arrestResponse.setResultCode(BUSINESS_ERROR);
                arrestResponse.setDecryption("not find Arrest");
            }
        }
        return arrestResponse;
    }

    public ArrestResponse createArrest(Client client, ArrestRequest arrestRequest, ArrestResponse arrestResponse) {
        ArrestDTO arrestDTO = arrestRequest.getArrestDTO();
        if (arrestDTO.getOperation() == FIRST_OPERATION) {
            Optional<Arrest> findArrest = findByClientAndByDocNum(client, arrestRequest);
            if (!findArrest.isPresent()) {
                Arrest arrest = getArrest(arrestRequest).get();
                arrest.setClient(client);
                client.addArrestToClient(arrest);
                saveArrest(arrest);
                arrestResponse.setId(arrest.getId());
            } else {
                arrestResponse.setResultCode(BUSINESS_ERROR);
                arrestResponse.setDecryption("this arrest is already present");
            }
        }
        return arrestResponse;

    }

}
