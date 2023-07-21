package com.gdsc.CGLH.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestWaste {
    @NotNull
    private String state; //    시/도
    @NotNull
    private String centerName; //   군/구
    @NotNull
//    @NotBlank
    private LocalDate requestDate;
}
