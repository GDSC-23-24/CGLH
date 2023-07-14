package com.gdsc.CGLH.service;

import com.gdsc.CGLH.dto.UserDetailsDto;
import com.gdsc.CGLH.dto.role.Role;
import com.gdsc.CGLH.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DataJpaTest
class MemberDetailsServiceImplTest {

    @MockBean
    UserDetailsServiceImpl userDetailsService;

    @MockBean
    UserRepository userRepository;

    @DisplayName("정상 로그인 테스트")
    @Test
    public void loadUserByUsernameTest() {
        // UserDetails 객체 생성
        UserDetails userDetails = new UserDetailsDto(1L,"username", "password", "채승지",Role.ROLE_USER);

        // userDetailsService의 loadUserByUsername 메소드가 "username"을 인수로 호출될 때 userDetails 객체를 반환하도록 설정
        when(userDetailsService.loadUserByUsername("username")).thenReturn(userDetails);

        // loadUserByUsername 메소드가 정상적으로 UserDetails 객체를 반환하는지 검증
        UserDetails result = userDetailsService.loadUserByUsername("username");
        assertEquals("username", result.getUsername());
    }

    @DisplayName("비정상 로그인 테스트")
    @Test
    public void loadUserByUsernameFailTest() {
        // userDetailsService의 loadUserByUsername 메소드가 호출될 때 UsernameNotFoundException 예외를 발생시키도록 설정
        when(userDetailsService.loadUserByUsername(anyString())).thenThrow(new UsernameNotFoundException("User not found"));

        // 예외가 발생하는지 검증
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername("test"));
    }
}