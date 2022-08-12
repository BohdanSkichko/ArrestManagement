package com.example.arrestmanagement.controller;

import com.example.arrestmanagement.model.Arrest;
import com.example.arrestmanagement.service.ArrestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ArrestController {
    @Autowired
    private final ArrestService arrestService;

    public ArrestController(ArrestService arrestService) {
        this.arrestService = arrestService;
    }

    @PostMapping(value = "/arrests/{id} {amount} {purpose}")

    public Arrest updateArrestAmountAndPurposeById(
            @PathVariable Long id, @PathVariable Long amount, @PathVariable String purpose) {
        return arrestService.updateArrestAmountAndPurposeById(id, amount, purpose);
    }

    @PostMapping(value = "/arrests")
    public Arrest saveArrest(@RequestBody Arrest arrest) {
        return arrestService.saveArrest(arrest);
    }

    @PostMapping(value = "/arrests/{id}")
    public Arrest canceledArrestById(@PathVariable Long id) {
        return arrestService.canceledArrestByID(id);
    }
}
