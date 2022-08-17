package com.example.arrestmanagement.controller;

import com.example.arrestmanagement.exception_handling.ClientIncorrectException;
import com.example.arrestmanagement.exception_handling.NoSuchClientException;
import com.example.arrestmanagement.entity.Client;
import com.example.arrestmanagement.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private final ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/clients/get/{name} {surname}")
    public Optional<Client> getClientByNameAndSurname(@PathVariable String name, @PathVariable String surname) {
        if (name == null || surname == null) {
            throw new ClientIncorrectException("Incorrect input");
        }
        return clientService.findClientByFirstNameAndLastName(name, surname);
    }

    @GetMapping(value = "/clients/{id}")
    public Optional<Client> getClient(@PathVariable Long id) {
        if (id == null) {
            throw new NoSuchClientException("No such Client " + id + " in Database");
        }
        return clientService.findById(id);
    }

    @PostMapping(value = "/clients")
    public Client saveClient(@RequestBody Client client) {
        if (client == null) {
            throw new ClientIncorrectException("Client null");
        }
        return clientService.saveClient(client);
    }
}
