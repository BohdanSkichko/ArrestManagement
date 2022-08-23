package com.example.arrestmanagement.repository;

import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.entity.Arrest;
import com.example.arrestmanagement.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface ArrestRepository extends JpaRepository<Arrest, Long> {
    Optional<Arrest> findArrestByDocNum(String docNum);

    Optional<Arrest> findArrestByClientAndDocNum(Client client, String docNum);
    Optional<Arrest> findArrestByClient(Client client);
    List<Arrest> findArrestsByClient(Client client);


}
