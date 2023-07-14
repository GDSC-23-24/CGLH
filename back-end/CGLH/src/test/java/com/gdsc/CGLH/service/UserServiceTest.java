package com.gdsc.CGLH.service;

import com.gdsc.CGLH.dto.UserDto;
import com.gdsc.CGLH.dto.UserJoinFormDto;
import com.gdsc.CGLH.dto.role.Role;
import com.gdsc.CGLH.entity.User;
import com.gdsc.CGLH.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDto userDto;

    @BeforeEach
    public void setUp() {
        user = User.builder()
                .loginId("testLoginId")
                .password("testPassword")
                .nickname("testNickname")
                .role(Role.ROLE_USER)
                .build();

        userDto = new UserDto(user);
    }

    @DisplayName("정상로그인")
    @Test
    public void whenValidLoginIdAndPassword_thenShouldReturnUser() {
        when(userRepository.findByLoginId(user.getLoginId())).thenReturn(Optional.of(user));

        UserDto foundUserDto = userService.login(user.getLoginId(), user.getPassword());

        assertNotNull(foundUserDto);
        assertEquals(user.getLoginId(), foundUserDto.getLoginId());
    }

    @DisplayName("존재하지 않는 로그인 ID")
    @Test
    public void whenInvalidLoginId_thenShouldThrowException() {
        when(userRepository.findByLoginId(anyString())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userService.login("invalidLoginId", user.getPassword());
        });
    }

    @DisplayName("잘못된 패스워드")
    @Test
    public void whenInvalidPassword_thenShouldThrowException() {
        when(userRepository.findByLoginId(user.getLoginId())).thenReturn(Optional.of(user));

        assertThrows(BadCredentialsException.class, () -> {
            userService.login(user.getLoginId(), "invalidPassword");
        });
    }
}
