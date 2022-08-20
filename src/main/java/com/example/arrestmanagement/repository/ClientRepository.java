package com.example.arrestmanagement.repository;

import com.example.arrestmanagement.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findClientByFirstNameAndLastNameAndDulTypeAndNumSeries(
            String firstName, String lastName, Integer dulType, String numSeries);

}
