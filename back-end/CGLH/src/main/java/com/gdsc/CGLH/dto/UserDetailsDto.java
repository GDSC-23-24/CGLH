package com.gdsc.CGLH.dto;

import com.gdsc.CGLH.dto.role.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Delegate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


@Getter
public class UserDetailsDto implements UserDetails {

    private Long id;
    private String loginId;
    private String password;
    private String nickname;
    private Role role;

    public UserDetailsDto(Long id, String loginId, String password, String nickname, Role role) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(this.role.toString())); //권한
        return auth;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
