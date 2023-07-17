package com.gdsc.CGLH.config;

import com.gdsc.CGLH.dto.role.Role;
import com.gdsc.CGLH.entity.Member;
import com.gdsc.CGLH.entity.Waste;
import com.gdsc.CGLH.entity.WasteStatus;
import com.gdsc.CGLH.repository.UserRepository;
import com.gdsc.CGLH.repository.WasteRepository.WasteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Date;

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
        private final UserRepository userRepository;
        private final WasteRepository wasteRepository;
        public void initMember() {

            Member member1 = Member.builder().loginId("abcde1").password("abcdefg1!").role(Role.ROLE_USER)
                    .nickname("채승지").build();

            Member member2 = Member.builder().loginId("abcde2").password("abcdefg2!").role(Role.ROLE_USER)
                    .nickname("채승지2").build();



            userRepository.save(member1);
            userRepository.save(member2);


            Waste waste = Waste.builder().id(1L)
                    .state("부산광역시").centerName("강서구 센터").status(WasteStatus.REFUSE)
                    .requestDate(LocalDateTime.now())
                    .member(member1).build();

            Waste waste1 = Waste.builder().state("부산광역시").centerName("강서구 센터").status(WasteStatus.REFUSE)
                    .requestDate(LocalDateTime.now())
                    .member(member1).build();

            Waste waste2 = Waste.builder().state("부산광역시").centerName("사하구 센터").status(WasteStatus.WAITING)
                    .requestDate(LocalDateTime.now().plusDays(1)).member(member1).build();

            Waste waste3 = Waste.builder().state("부산광역시").centerName("강서구 센터").status(WasteStatus.WAITING)
                    .requestDate(LocalDateTime.now().plusDays(1))
                    .member(member1).build();

            Waste waste4 = Waste.builder().state("부산광역시").centerName("강서구 센터").status(WasteStatus.WAITING)
                    .requestDate(LocalDateTime.now().plusDays(1))
                    .member(member1).build();

            Waste waste5 = Waste.builder().state("부산광역시").centerName("강서구 센터").status(WasteStatus.PERMIT)
                    .requestDate(LocalDateTime.now().plusDays(1))
                    .member(member1).build();

            wasteRepository.save(waste);

            wasteRepository.save(waste1);
            wasteRepository.save(waste2);
            wasteRepository.save(waste3);
            wasteRepository.save(waste4);
            wasteRepository.save(waste5);

        }

    }


}
