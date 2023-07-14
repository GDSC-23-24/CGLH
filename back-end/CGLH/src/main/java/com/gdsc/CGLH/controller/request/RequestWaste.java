package com.gdsc.CGLH.controller.request;


import lombok.AllArgsConstructor;
import lombok.Data;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RequestWaste {
    @NotNull
    private String state; //    시/도
    @NotNull
    private String county; //   군/구
    @NotNull
    private String town; //     읍/면/동
//    @NotBlank
    private LocalDateTime requestDate;
}
