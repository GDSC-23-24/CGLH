package com.gdsc.CGLH.repository.WasteRepository;

import com.gdsc.CGLH.entity.Waste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WasteRepository extends JpaRepository<Waste, Long> {

    Optional<Waste> findById(String wasteId);



}
