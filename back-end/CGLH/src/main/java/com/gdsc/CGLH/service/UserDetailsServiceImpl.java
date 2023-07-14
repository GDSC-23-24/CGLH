package com.gdsc.CGLH.service;

import com.gdsc.CGLH.dto.UserDetailsDto;
import com.gdsc.CGLH.entity.Member;
import com.gdsc.CGLH.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * 안쓰는 파일
 */
@Service
public class    UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Optional<Member> findUser = userRepository.findByLoginId(loginId);
        if(!findUser.isPresent()){
            throw new UsernameNotFoundException("가입되지 않은 회원입니다.");
        }
        Member member = findUser.get();

        return new UserDetailsDto(member.getId(), member.getLoginId(), member.getPassword(), member.getNickname(), member.getRole()); // 세션에 UserDetailsDto저장.
    }
}
