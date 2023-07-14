package com.gdsc.CGLH.service;

import com.gdsc.CGLH.entity.Location;
import com.gdsc.CGLH.repository.LocationRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

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
