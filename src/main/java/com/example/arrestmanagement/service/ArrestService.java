package com.example.arrestmanagement.service;

import com.example.arrestmanagement.entity.Arrest;
import com.example.arrestmanagement.entity.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ArrestService {



    Arrest updateArrest(Arrest arrest);

    Optional<Arrest> findArrestById(Long id);

    Arrest saveArrest(Arrest arrest);

    Optional<Arrest> findArrestByDocNum(String docNUm);
    List<Arrest> findAllByClient(Client client);

    Optional<Arrest> findByClientAndByDocNm(Client client, String docNum);
    Optional<Arrest> findByClient (Client client);

    Optional<Arrest> update(Arrest arrest);

    Arrest updateArrestAmountAndPurposeById(Long id, Long amount, String purpose);

    Arrest canceledArrestByID(Long id);
}
