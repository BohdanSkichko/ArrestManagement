package com.example.arrestmanagement.service.impl;

import com.example.arrestmanagement.dictionary.DocTypeDictionary;
import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.dto.IdentDocDto;
import com.example.arrestmanagement.entity.Client;
import com.example.arrestmanagement.parameter.InnerIdentDoc;
import com.example.arrestmanagement.parameter.OuterIdentDoc;
import com.example.arrestmanagement.repository.ClientRepository;
import com.example.arrestmanagement.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;


    public Optional<Client> findClient(Client client) {
        String firstName = client.getFirstName();
        String lastName = client.getLastName();
        String numSeries = client.getNumSeries();
        int idType = client.getIdType();
        return clientRepository.findClientByFirstNameAndLastNameAndIdTypeAndNumSeries(
                firstName, lastName, idType, numSeries);

    }


    public void saveClient(Client client) {
        clientRepository.save(client);
    }


    public Client saveNewClientOrGetFromDB(Client client) {
        Optional<Client> clientInDB = findClient(client);
        if (clientInDB.isPresent()) {
            return clientInDB.get();
        }
        saveClient(client);
        return findClient(client).get();

    }

    public InnerIdentDoc createClientsFormatFromRequest(ArrestRequest arrestRequest) {
        IdentDocDto identDocDT0 = arrestRequest.getIdentDocDTO();
        String numSeries = identDocDT0.getNumberSeries();
        int code = arrestRequest.getOrganCode();
        int type = identDocDT0.getType();
        OuterIdentDoc outerIdentDoc = new OuterIdentDoc();
        outerIdentDoc.setType(type);
        outerIdentDoc.setNumSeries(numSeries);
        outerIdentDoc.setOrganCode(code);
        return DocTypeDictionary.getClientIdentDocFromArrest(outerIdentDoc);
    }

    @Override
    public Client getClientFromRequest(ArrestRequest arrestRequest) {
        Client client = new Client();
        client.setFirstName(arrestRequest.getFirstName());
        client.setLastName(arrestRequest.getLastname());
        InnerIdentDoc innerIdentDoc = createClientsFormatFromRequest(arrestRequest);
        client.setNumSeries(innerIdentDoc.getNumSeries());
        client.setIdType(innerIdentDoc.getIdType());
        return saveNewClientOrGetFromDB(client);
    }

}
