package com.gdsc.CGLH.service;

import com.gdsc.CGLH.dto.UserDto;
import com.gdsc.CGLH.dto.role.Role;
import com.gdsc.CGLH.entity.Member;
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
public class MemberServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private Member member;
    private UserDto userDto;

    @BeforeEach
    public void setUp() {
        member = Member.builder()
                .loginId("testLoginId")
                .password("testPassword")
                .nickname("testNickname")
                .role(Role.ROLE_USER)
                .build();

        userDto = new UserDto(member);
    }

    @DisplayName("정상로그인")
    @Test
    public void whenValidLoginIdAndPassword_thenShouldReturnUser() {
        when(userRepository.findByLoginId(member.getLoginId())).thenReturn(Optional.of(member));

        UserDto foundUserDto = userService.login(member.getLoginId(), member.getPassword());

        assertNotNull(foundUserDto);
        assertEquals(member.getLoginId(), foundUserDto.getLoginId());
    }

    @DisplayName("존재하지 않는 로그인 ID")
    @Test
    public void whenInvalidLoginId_thenShouldThrowException() {
        when(userRepository.findByLoginId(anyString())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userService.login("invalidLoginId", member.getPassword());
        });
    }

    @DisplayName("잘못된 패스워드")
    @Test
    public void whenInvalidPassword_thenShouldThrowException() {
        when(userRepository.findByLoginId(member.getLoginId())).thenReturn(Optional.of(member));

        assertThrows(BadCredentialsException.class, () -> {
            userService.login(member.getLoginId(), "invalidPassword");
        });
    }
}
