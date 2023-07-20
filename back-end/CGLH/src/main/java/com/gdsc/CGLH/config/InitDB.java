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
                    .nickname("이태엽").build();


            Member member3 = Member.builder().loginId("abcde2").password("abcdefg2!").role(Role.ROLE_USER)
                    .nickname("하윤지").build();

            Member member4 = Member.builder().loginId("abcde2").password("abcdefg2!").role(Role.ROLE_USER)
                    .nickname("고봉훈").build();


            userRepository.save(member1);
            userRepository.save(member2);
            userRepository.save(member3);
            userRepository.save(member4);


            Waste waste = makeWaste("부산광역시", "강서구 센터", WasteStatus.REFUSE, 0, member1);

            Waste waste1 = makeWaste("부산광역시", "강서구센터", WasteStatus.PERMIT, 0, member1);

            Waste waste2 = makeWaste("부산광역시", "사하구 센터", WasteStatus.WAITING, 1, member1);

            Waste waste3 = makeWaste("부산광역시", "강서구 센터", WasteStatus.WAITING, 2, member3);

            Waste waste4 = makeWaste("부산광역시", "강서구 센터", WasteStatus.WAITING, 3, member2);

            Waste waste5 = makeWaste("부산광역시", "강서구 센터", WasteStatus.REFUSE, 4, member4);

            Waste waste6 = makeWaste("부산광역시", "강서구 센터", WasteStatus.PERMIT, 5, member3);

            Waste waste7 = makeWaste("부산광역시", "강서구 센터", WasteStatus.REFUSE, 6, member1);

            Waste waste8 = makeWaste("부산광역시", "강서구 센터", WasteStatus.REFUSE, 7, member4);

            Waste waste9 = makeWaste("부산광역시", "강서구 센터", WasteStatus.WAITING, 8, member1);

            Waste waste10 = makeWaste("부산광역시", "강서구 센터", WasteStatus.PERMIT, 9, member3);

            Waste waste11 = makeWaste("부산광역시", "강서구 센터", WasteStatus.PERMIT, 8, member2);

            wasteRepository.save(waste);
            wasteRepository.save(waste1);
            wasteRepository.save(waste2);
            wasteRepository.save(waste3);
            wasteRepository.save(waste4);
            wasteRepository.save(waste5);
            wasteRepository.save(waste6);
            wasteRepository.save(waste7);
            wasteRepository.save(waste8);
            wasteRepository.save(waste9);
            wasteRepository.save(waste10);
            wasteRepository.save(waste11);
            wasteRepository.save(makeWaste("부산광역시", "강서구 센터", WasteStatus.PERMIT, 10, member1));
            wasteRepository.save(makeWaste("부산광역시", "강서구 센터", WasteStatus.REFUSE, 10, member1));
            wasteRepository.save(makeWaste("부산광역시", "강서구 센터", WasteStatus.WAITING, 10, member1));
            wasteRepository.save(makeWaste("부산광역시", "강서구 센터", WasteStatus.WAITING, 10, member3));
            wasteRepository.save(makeWaste("부산광역시", "강서구 센터", WasteStatus.WAITING, 10, member4));
            wasteRepository.save(makeWaste("부산광역시", "강서구 센터", WasteStatus.PERMIT, 10, member2));


        }

        public Waste makeWaste(String state, String centerName, WasteStatus status, int day, Member member) {
            Waste waste = Waste.builder().state(state).centerName(centerName).status(status)
                    .requestDate(LocalDateTime.now().plusDays(day))
                    .member(member).build();
            return waste;
        }

    }


}
