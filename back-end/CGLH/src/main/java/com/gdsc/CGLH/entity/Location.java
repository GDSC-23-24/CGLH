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
<<<<<<< HEAD
    private String state;
    private String centerName;
=======
    private String state; // 시/도
    private String centerName; // 센터
>>>>>>> 1eccaa4902298089d25b72e715f4932270a8e665
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
