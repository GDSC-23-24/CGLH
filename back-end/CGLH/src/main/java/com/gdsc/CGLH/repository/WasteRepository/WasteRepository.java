package com.gdsc.CGLH.repository.WasteRepository;

import com.gdsc.CGLH.dto.response.WasteScheduleDto;
import com.gdsc.CGLH.entity.Waste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WasteRepository extends JpaRepository<Waste, Long> {

    Optional<Waste> findById(String wasteId);

    @Query(value = "SELECT * FROM waste WHERE member_id = :memberId", nativeQuery = true)
    List<Waste> findAllByMemberId(@Param("memberId") Long memberId);


    @Query("SELECT w FROM Waste w WHERE w.centerName = :centerName")
    List<Waste> findByCenterName(@Param("centerName") String centerName);
}
