package com.example.arrestmanagement.service;

import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.entity.Client;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public interface ClientService {
    List<Client> findAll();

    Optional<Client> findById(Long id);

    @Transient
    Client saveClient(Client client);

    Client updateClient(Client client);

    Client getClient(ArrestRequest arrestRequest);

    void deleteClient(Long id);

    Optional<Client> findClient(Client client);

    Client saveNewClientOrGetFromDB(Client client);
}

