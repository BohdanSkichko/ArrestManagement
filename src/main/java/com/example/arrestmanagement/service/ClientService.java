package com.example.arrestmanagement.service;

import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.entity.Client;
import com.example.arrestmanagement.helper.client.passport.service.ClientIdentDoc;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public interface ClientService {

    @Transient
    Client saveClient(Client client);


    Client getClientFromRequest(ArrestRequest arrestRequest);


    Optional<Client> findClient(Client client);

    Client saveNewClientOrGetFromDB(Client client);
    ClientIdentDoc createClientsFormatFromRequest (ArrestRequest arrestRequest);
}

