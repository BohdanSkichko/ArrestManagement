package com.example.arrestmanagement.service.impl;

import com.example.arrestmanagement.dto.ArrestRequest;
import com.example.arrestmanagement.entity.Arrest;
import com.example.arrestmanagement.repository.ArrestRepository;
import com.example.arrestmanagement.service.ArrestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class ArrestServiceImpl implements ArrestService {

    @Autowired
    private final ArrestRepository arrestRepository;

    public ArrestServiceImpl(ArrestRepository arrestRepository) {
        this.arrestRepository = arrestRepository;
    }


    @Override
    public Arrest updateArrest(Arrest arrest) {
        return arrestRepository.save(arrest);
    }

    @Override
    public Optional<Arrest> findArrestById(Long id) {
        return Optional.empty();
    }


    @Override
    public Arrest saveArrest(Arrest arrest) {

        return arrestRepository.save(arrest);
    }

    @Override
    public Arrest updateArrestAmountAndPurposeById(Long id, Long amount, String purpose) {

        return null;
    }

    @Override
    public Arrest canceledArrestByID(Long id) {
        return null;
    }


}
