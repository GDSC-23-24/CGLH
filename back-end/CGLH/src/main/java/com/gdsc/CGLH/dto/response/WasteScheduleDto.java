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
    private String status;
    private String nickname;

    public WasteScheduleDto(Waste entity) {
        this.id = entity.getId();
        this.centerName = entity.getCenterName();
        this.requestDate = entity.getRequestDate();

        WasteStatus wasteStatus = entity.getStatus();

        if (wasteStatus == WasteStatus.PERMIT) this.status = "승인";
        else if (wasteStatus == WasteStatus.WAITING) this.status = "대기";
        else this.status = "거절";

        this.nickname = entity.getMember().getNickname();
    }

    public static WasteScheduleDto from(Waste entity) {
        return new WasteScheduleDto(entity);
    }


}
