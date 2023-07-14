package com.gdsc.CGLH.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Waste extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "waste_id")
    private Long id;


    private String state; //    시/도

    private String county_center; //   군/구

    private LocalDateTime requestDate; // 신청일자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
