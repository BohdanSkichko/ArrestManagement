package com.example.arrestmanagement.service;

import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.entity.Client;
import com.example.arrestmanagement.parameter.InnerIdentDoc;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public interface ClientService  {


    Client saveClient(Client client);


    Client getClientFromRequest(ArrestRequest arrestRequest);


    Optional<Client> findClient(Client client);

    Client saveNewClientOrGetFromDB(Client client);
    InnerIdentDoc createClientsFormatFromRequest (ArrestRequest arrestRequest);
}

