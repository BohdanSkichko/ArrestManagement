package com.example.arrestmanagement.service;

import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.entity.Client;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {

    Client getClientFromRequest(ArrestRequest arrestRequest);

}

