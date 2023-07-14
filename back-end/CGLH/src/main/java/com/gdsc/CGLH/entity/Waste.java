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

    private String centerName; //   군/구

    private WasteStatus status;

    private LocalDateTime requestDate; // 신청일자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;
}
