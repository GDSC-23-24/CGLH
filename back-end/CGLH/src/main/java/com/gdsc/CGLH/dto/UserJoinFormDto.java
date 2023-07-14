package com.gdsc.CGLH.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class UserJoinFormDto {
    private String loginId;
    private String password;
    private String nickname; //닉네임
}
