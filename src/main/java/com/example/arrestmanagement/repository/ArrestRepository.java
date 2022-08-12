package com.example.arrestmanagement.repository;

import com.example.arrestmanagement.model.Arrest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ArrestRepository extends JpaRepository<Arrest, Long> {

    public Optional<Arrest> findArrestById(Long id);

}
