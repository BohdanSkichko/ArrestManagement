package com.example.arrestmanagement.service;

import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.dto.ArrestResponse;
import com.example.arrestmanagement.entity.Client;
import org.springframework.stereotype.Service;

@Service
public interface ArrestService {


    void cancelArrest(Client client, ArrestRequest arrestRequest, ArrestResponse arrestResponse);

    void createArrest(Client client, ArrestRequest arrestRequest, ArrestResponse arrestResponse);

    void editArrest(Client client, ArrestRequest arrestRequest, ArrestResponse arrestResponse);


}
