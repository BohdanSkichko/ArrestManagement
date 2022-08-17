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
    public Arrest save(ArrestRequest arrestRequest) {
        return arrestRepository.save(arrestRequest);
    }

    @Override
    public Arrest updateArrest(Arrest arrest) {
        return arrestRepository.save(arrest);
    }

    @Override
    public Optional<Arrest> findArrestById(Long id) {
        return arrestRepository.findArrestById(id);
    }

    @Override
    public Arrest saveArrest(Arrest arrest) {

        return arrestRepository.save(arrest);
    }

    public Arrest updateArrestAmountAndPurposeById(Long id, Long amount, String purpose) {
        Arrest arrest = arrestRepository.findArrestById(id).get();

//        Long sum = arrest.getAmount();
//        Long diff = sum - Math.abs(amount);
//        if (diff == 0){
//            arrest.setStatus(Arrest.Status.CANCELED);
//        }
        if (amount == 0) {
            arrest.setStatus(Arrest.Status.CANCELED);
        }
        arrest.setAmount(amount);
        arrest.setPurpose(purpose);
        return arrestRepository.save(arrest);
    }

    @Override
    public Arrest canceledArrestByID(Long id) {
        Arrest arrest = arrestRepository.findArrestById(id).get();
        arrest.setStatus(Arrest.Status.CANCELED);
        return arrest;
    }

}
