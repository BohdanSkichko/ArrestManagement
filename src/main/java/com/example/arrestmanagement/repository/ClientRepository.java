package com.example.arrestmanagement.repository;

import com.example.arrestmanagement.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    public Optional<Client> findClientByFirstNameAndLastName(String name, String surname);
}
