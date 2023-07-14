package com.gdsc.CGLH.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserJoinFormDto {
    private String loginId;
    private String password;
    private String nickname; //닉네임
}
