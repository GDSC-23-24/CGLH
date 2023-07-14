package com.gdsc.CGLH.service;

import com.gdsc.CGLH.entity.Location;
import com.gdsc.CGLH.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    /**
     * save, find
     */
    @Transactional // 쓰기모드
    public void save(Location location) {locationRepository.save(location);}

    public Location findOne(Long locationId) {return locationRepository.findOne(locationId);}
    public List<Location> findAll() {return locationRepository.findAll();}

}
