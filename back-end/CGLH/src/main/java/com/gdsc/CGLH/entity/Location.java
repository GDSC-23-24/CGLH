package com.gdsc.CGLH.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Location {
    @Id
    @GeneratedValue
    @Column(name = "location_id")
    private Long id;
    private String state;
    private String centerName;
    private String address;
    private String zip;
    private String phone;


    public static Location createLocation(String name, String address, String zip, String phone) {
        Location location = new Location();
        String[] temp = name.split(" ",2);
        location.state = temp[0];
        location.centerName = temp[1];
        location.address = address;
        location.zip = zip;
        location.phone = phone;
        return location;
    }
}
