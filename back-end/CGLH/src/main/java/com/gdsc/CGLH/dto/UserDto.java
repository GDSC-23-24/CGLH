package com.gdsc.CGLH.dto;


import com.gdsc.CGLH.dto.role.Role;
import com.gdsc.CGLH.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String loginId;
    private String password;
    private String nickname;
    private Role role;

    public UserDto(Member member){
        this.id = member.getId();
        this.loginId = member.getLoginId();
        this.password = member.getPassword();
        this.nickname = member.getNickname();
        this.role = member.getRole();
    }

    @Builder
    public UserDto(Long id, String loginId, String password, String nickname, Role role) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }
}
