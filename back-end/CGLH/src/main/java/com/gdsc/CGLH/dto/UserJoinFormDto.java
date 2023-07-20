package com.gdsc.CGLH.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserJoinFormDto {
    @NotNull
    @NotBlank
    private String loginId;
    @NotNull
    @NotBlank
    private String password;
    @NotNull
    @NotBlank
    private String nickname; //닉네임
}
