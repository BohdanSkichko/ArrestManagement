package com.example.arrestmanagement.repository;

import com.example.arrestmanagement.model.Arrest;
import com.example.arrestmanagement.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    public Optional<Client> findClientByFirstNameAndLastName(String name, String surname);
}
