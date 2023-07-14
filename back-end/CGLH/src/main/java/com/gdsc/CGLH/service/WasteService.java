package com.gdsc.CGLH.service;

import com.gdsc.CGLH.dto.request.RequestWaste;
import com.gdsc.CGLH.dto.WasteDto;
import com.gdsc.CGLH.dto.response.WasteScheduleDto;
import com.gdsc.CGLH.entity.Member;
import com.gdsc.CGLH.entity.Waste;
import com.gdsc.CGLH.entity.WasteStatus;
import com.gdsc.CGLH.repository.UserRepository;
import com.gdsc.CGLH.repository.WasteRepository.WasteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class WasteService {

    private final WasteRepository wasteRepository;

    private final UserRepository userRepository;

    /**
     * 신청 내용 저장
     */
    public void saveWaste(RequestWaste requestWaste, String loginId) throws IOException {
        Member member = userRepository.findByLoginId(loginId).orElseThrow(
                () -> new EntityNotFoundException("회원이 존재하지 않습니다."));

        Waste waste = Waste.builder()
                .state(requestWaste.getState())
                .centerName(requestWaste.getCenterName())
                .status(WasteStatus.WAITING)
                .requestDate(requestWaste.getRequestDate())
                .member(member)
                .build();

        wasteRepository.save(waste);
    }

    /**
     *  신청 내용 전체 조회 (List)
     */
    public List<WasteDto> getWasteList(String loginId){
        Optional<Member> member = userRepository.findByLoginId(loginId);

        List<WasteDto> wasteDtoList =
                wasteRepository.findAllByMemberId(member.get().getId()).stream().map(WasteDto::from).collect(Collectors.toList());

        return wasteDtoList;
    }


    /**
     * 신청 내용 조회 단일
     */
    public WasteDto getWaste(Long wasteId) {
        Waste waste = wasteRepository.findById(wasteId).orElseThrow(()-> new EntityNotFoundException("신청 내역이 존재하지 않습니다."));
        return new WasteDto(waste);
    }



    /**
     * 신청 취소
     */
    public void deleteWaste(Long wasteId) {
        Waste waste = wasteRepository.findById(wasteId).orElseThrow(()-> new EntityNotFoundException("신청 내역이 존재하지 않습니다."));

        wasteRepository.delete(waste);
    }

    /**
     * 폐기 신청 작성자 조회
     */
    @Transactional(readOnly = true)
    public String findLoginId(Long wasteId){
        Waste waste = wasteRepository.findById(wasteId).orElseThrow(()-> new EntityNotFoundException("신청 내역이 존재하지 않습니다."));
        return waste.getMember().getLoginId();
    }

    public List<WasteScheduleDto> getCenterWastes(String centerName) {
        List<WasteScheduleDto> wasteScheduleDtoList =
                wasteRepository.findByCenterName(centerName).stream().map(WasteScheduleDto::from).collect(Collectors.toList());

        return wasteScheduleDtoList;
    }
}
