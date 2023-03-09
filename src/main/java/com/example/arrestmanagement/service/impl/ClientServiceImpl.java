package com.example.arrestmanagement.service.impl;

import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.dto.IdentDocDTO;
import com.example.arrestmanagement.entity.Client;
import com.example.arrestmanagement.parameter.OuterIdentDoc;
import com.example.arrestmanagement.dictionary.DocTypeDictionary;
import com.example.arrestmanagement.repository.ClientRepository;
import com.example.arrestmanagement.service.ClientService;
import com.example.arrestmanagement.parameter.InnerIdentDoc;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;



@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    @Autowired
    private final ClientRepository clientRepository;


    @Override
    public Optional<Client> findClient(Client client) {
        String firstName = client.getFirstName();
        String lastName = client.getLastName();
        String numSeries = client.getNumSeries();
        int dulType = client.getDulType();
        return clientRepository.findClientByFirstNameAndLastNameAndDulTypeAndNumSeries(
                firstName, lastName, dulType, numSeries);

    }


    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }


    public Client getClientFromRequest(ArrestRequest arrestRequest) {
        Client client = new Client();
        client.setFirstName(arrestRequest.getFirstName());
        client.setLastName(arrestRequest.getLastname());
        InnerIdentDoc innerIdentDoc = createClientsFormatFromRequest(arrestRequest);
        client.setNumSeries(innerIdentDoc.getNumSeries());
        client.setDulType(innerIdentDoc.getDulType());
        return saveNewClientOrGetFromDB(client);
    }

    public Client saveNewClientOrGetFromDB(Client client) {
        Optional<Client> clientInDB = findClient(client);
        if (clientInDB.isPresent()) {
            return clientInDB.get();
        }
        saveClient(client);
        return findClient(client).get();

    }

    @Override
    public InnerIdentDoc createClientsFormatFromRequest(ArrestRequest arrestRequest) {
        IdentDocDTO identDocDT0 = arrestRequest.getIdentDocDTO();
        String numSeries = identDocDT0.getNumberSeries();
        int code = arrestRequest.getOrganCode();
        int type = identDocDT0.getType();
        OuterIdentDoc outerIdentDoc = new OuterIdentDoc();
        outerIdentDoc.setType(type);
        outerIdentDoc.setNumSeries(numSeries);
        outerIdentDoc.setOrganCode(code);
        return DocTypeDictionary.getClientIdentDocFromArrest(outerIdentDoc);
    }

}
