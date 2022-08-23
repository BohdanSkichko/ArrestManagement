package com.example.arrestmanagement.service.impl;

import com.example.arrestmanagement.entity.Client;
import com.example.arrestmanagement.repository.ClientRepository;
import com.example.arrestmanagement.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> findClientByFirstNameAndLastNameAndDulTypeAndNumSeries(
            String firstName, String lastName, Integer dulType, String numSeries) {
        return clientRepository.findClientByFirstNameAndLastNameAndDulTypeAndNumSeries(
                firstName,lastName,dulType,numSeries);
    }


    @Override
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client saveClient(Client client) {


        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
