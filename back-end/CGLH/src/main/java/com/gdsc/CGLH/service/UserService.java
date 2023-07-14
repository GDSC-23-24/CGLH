package com.gdsc.CGLH.service;

import com.gdsc.CGLH.dto.UserDto;
import com.gdsc.CGLH.dto.UserJoinFormDto;
import com.gdsc.CGLH.dto.role.Role;
import com.gdsc.CGLH.entity.Member;
import com.gdsc.CGLH.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String register(UserJoinFormDto userDto) {
        Member member = Member.builder()
                .loginId(userDto.getLoginId())
                .password(userDto.getPassword())
                .nickname(userDto.getNickname())
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(member);
        return member.getLoginId();
    }

    public UserDto login(String loginId, String password) {
        Member member = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + loginId));

        if(!(password.equals(member.getPassword()))) {
            throw new BadCredentialsException("Invalid password!");
        }

        return new UserDto(member);
    }

    public Boolean validateLoginId(String loginId) {
        Optional<Member> userEntity = userRepository.findByLoginId(loginId);
        return userEntity.isPresent() ? false : true;
    }
}
