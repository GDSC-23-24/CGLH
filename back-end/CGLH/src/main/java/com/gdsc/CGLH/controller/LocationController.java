package com.gdsc.CGLH.controller;

import com.gdsc.CGLH.entity.Location;
import com.gdsc.CGLH.service.LocationService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/locations")
public class LocationController {
    private final LocationService locationService;

    /**
     * 스케줄링, find
     */

    @GetMapping
    public List<LocationDto> findAll() {
        List<Location> locations = locationService.findAll();
        List<LocationDto> result = locations.stream()
                .map(o -> new LocationDto(o))
                .collect(Collectors.toList());
        return result;
    }

    // 매주 일요일 00시 최신화
    @Scheduled(cron = "0 0 0 * * SUN")
    public void updateLocations() {
        // pass
    }

    // 임시 저장 Test -> http://localhost:8080/api/v1/locations/new
    @GetMapping(value="/new")
    public ResponseEntity<String> saveTest() throws URISyntaxException {
        // api 호출
        String url1 = baseUrl+"serviceKey="+myAuthKey1+"&perPage=6000&page=1";
        String url2 = baseUrl+"serviceKey="+myAuthKey2+"&perPage=6000&page=1";
        String url3 = baseUrl+"serviceKey="+myAuthKey1+"&perPage=6000&page=2";
        String url4 = baseUrl+"serviceKey="+myAuthKey2+"&perPage=6000&page=2";
        URI uri1 = new URI(url1); // URI 코드 % -> 25 encoding 방지.
        URI uri2 = new URI(url2);
        URI uri3 = new URI(url3);
        URI uri4 = new URI(url4);

        int totalCount=0;
        ApiRequestDto response1, response2;
        RestTemplate restTemplate = new RestTemplate();
        try {
            response1 = restTemplate.getForObject(uri1, ApiRequestDto.class);
            response2 = restTemplate.getForObject(uri3, ApiRequestDto.class);
        } catch (Exception e){
            response1 = restTemplate.getForObject(uri2, ApiRequestDto.class);
            response2 = restTemplate.getForObject(uri4, ApiRequestDto.class);
        }

        // data 가공
        for(ApiRequestDataDto obj : response1.getData()) {
            if(!obj.최하위기관명.equals("농업기술센터")) continue;
            Location location = Location.createLocation(
                    obj.전체기관명, obj.도로명주소, obj.새우편번호, obj.대표전화번호
            );
            locationService.save(location);
            totalCount++;
        }
        for(ApiRequestDataDto obj : response2.getData()) {
            if(!obj.최하위기관명.equals("농업기술센터")) continue;
            Location location = Location.createLocation(
                    obj.전체기관명, obj.도로명주소, obj.새우편번호, obj.대표전화번호
            );
            locationService.save(location);
            totalCount++;
        }
        System.out.println(totalCount);
        return ResponseEntity.status(HttpStatus.CREATED).body("추가 완료");
    }



    // DTO
    @Getter
    static class LocationDto {
        private String name, address, zip, phone, state;
        public LocationDto(Location location) {
            state = location.getState();
            name = location.getName();
            address = location.getAddress();
            zip = location.getZip();
            phone = location.getPhone();
        }
    }
    @Getter
    static class ApiRequestDto {
        private String totalCount, currentCount;
        private List<ApiRequestDataDto> data;
    }
    @Getter
    static class ApiRequestDataDto {
        private String 전체기관명, 대표전화번호, 새우편번호, 도로명주소, 최하위기관명;
    }

    // api
    private final String baseUrl = "https://api.odcloud.kr/api/15061082/v1/uddi:1964aff1-e1b7-468d-9a4d-f90219a47436?";
    @Value("${my.auth.key1}")
    private String myAuthKey1;
    @Value("${my.auth.key2}")
    private String myAuthKey2;
}
