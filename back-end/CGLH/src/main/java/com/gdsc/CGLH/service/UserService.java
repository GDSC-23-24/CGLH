package com.gdsc.CGLH.service;

import com.gdsc.CGLH.dto.UserDto;
import com.gdsc.CGLH.dto.UserJoinFormDto;
import com.gdsc.CGLH.dto.role.Role;
import com.gdsc.CGLH.entity.User;
import com.gdsc.CGLH.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String register(UserJoinFormDto userDto) {
        User user = User.builder()
                .loginId(userDto.getLoginId())
                .password(userDto.getPassword())
                .nickname(userDto.getNickname())
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);
        return user.getLoginId();
    }

    public UserDto login(String loginId, String password) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + loginId));

        if(!(password.equals(user.getPassword()))) {
            throw new BadCredentialsException("Invalid password!");
        }

        return new UserDto(user);
    }

    public Boolean validateLoginId(String loginId) {
        Optional<User> userEntity = userRepository.findByLoginId(loginId);
        return userEntity.isPresent() ? false : true;
    }
}
