package com.gdsc.CGLH.dto.response;

import com.gdsc.CGLH.entity.Waste;
import com.gdsc.CGLH.entity.WasteStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WasteScheduleDto {
    private Long id;
    private String centerName;
    private LocalDateTime requestDate;
    private WasteStatus status;
    private String nickname;

    public WasteScheduleDto(Waste entity) {
        this.id = entity.getId();
        this.centerName = entity.getCenterName();
        this.requestDate = entity.getRequestDate();
        this.status = entity.getStatus();
        this.nickname = entity.getMember().getNickname();
    }

    public static WasteScheduleDto from(Waste entity) {
        return new WasteScheduleDto(entity);
    }


}
