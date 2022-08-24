package com.example.arrestmanagement.service;

import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.dto.ArrestResponse;
import com.example.arrestmanagement.entity.Arrest;
import com.example.arrestmanagement.entity.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ArrestService {


    Arrest updateArrest(Arrest arrest);
    Arrest saveArrest(Arrest arrest);

    ArrestResponse canceledArrest(Client client, ArrestRequest arrestRequest, ArrestResponse arrestResponse);

    ArrestResponse createArrest (Client client,ArrestRequest arrestRequest, ArrestResponse arrestResponse);

    ArrestResponse editArrest(Client client, ArrestRequest arrestRequest, ArrestResponse arrestResponse);
    Optional<Arrest> findByClientAndByDocNum(Client client, ArrestRequest arrestRequest);

    Optional<Arrest> findByClientAndByRefDocNum(Client client, ArrestRequest arrestRequest);

    Optional<Arrest> getArrest(ArrestRequest arrestRequest);

}
