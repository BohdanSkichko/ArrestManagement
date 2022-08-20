package com.example.arrestmanagement.service;

import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.entity.Arrest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public interface ArrestService {



    Arrest updateArrest(Arrest arrest);

    Optional<Arrest> findArrestById(Long id);

    Arrest saveArrest(Arrest arrest);

    Arrest updateArrestAmountAndPurposeById(Long id, Long amount, String purpose);

    Arrest canceledArrestByID(Long id);
}
