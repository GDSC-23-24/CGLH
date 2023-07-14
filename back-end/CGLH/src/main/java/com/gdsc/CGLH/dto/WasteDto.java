package com.gdsc.CGLH.dto;


import com.gdsc.CGLH.entity.Waste;
import com.gdsc.CGLH.entity.WasteStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WasteDto {
    private Long id;
    private String state; //    시/도

    private String centerName; //   군/구

    private WasteStatus status; //     읍/면/동

    private LocalDateTime requestDate;


    public WasteDto(Waste entity) {

        this.id = entity.getId();
        this.state = entity.getState();
        this.centerName = entity.getCenterName();
        this.status = entity.getStatus();
        this.requestDate = entity.getRequestDate();

    }

}
