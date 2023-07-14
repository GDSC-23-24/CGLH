package com.gdsc.CGLH.dto;


import com.gdsc.CGLH.dto.role.Role;
import com.gdsc.CGLH.entity.User;
import lombok.AllArgsConstructor;
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

    public UserDto(User user){
        this.id = user.getId();
        this.loginId = user.getLoginId();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
        this.role = user.getRole();
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
