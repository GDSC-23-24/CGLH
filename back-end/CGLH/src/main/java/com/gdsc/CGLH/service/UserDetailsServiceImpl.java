package com.gdsc.CGLH.service;

import com.gdsc.CGLH.dto.UserDetailsDto;
import com.gdsc.CGLH.dto.UserDto;
import com.gdsc.CGLH.entity.User;
import com.gdsc.CGLH.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class    UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Optional<User> findUser = userRepository.findByLoginId(loginId);
        if(!findUser.isPresent()){
            throw new UsernameNotFoundException("가입되지 않은 회원입니다.");
        }
        User user = findUser.get();

        return new UserDetailsDto(user.getId(),user.getLoginId(),user.getPassword(),user.getNickname(),user.getRole()); // 세션에 UserDetailsDto저장.
    }
}
