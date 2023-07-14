package com.gdsc.CGLH.config;

import com.gdsc.CGLH.dto.role.Role;
import com.gdsc.CGLH.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;


    @PostConstruct
    public void init() {
        initService.initMember();
    }


    @Service
    @RequiredArgsConstructor
    @Transactional
    public static class InitService {

        private final EntityManager em;
        public void initMember() {
            Member member1 = Member.builder().loginId("abcde1").password("abcdefg1!").role(Role.ROLE_USER)
                    .nickname("채승지").build();



            em.persist(member1);


        }
    }


}
