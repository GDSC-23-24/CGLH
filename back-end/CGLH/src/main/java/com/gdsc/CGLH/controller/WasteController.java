package com.gdsc.CGLH.controller;


import com.gdsc.CGLH.config.JwtUtil;
import com.gdsc.CGLH.dto.request.RequestWaste;
import com.gdsc.CGLH.dto.WasteDto;
import com.gdsc.CGLH.dto.response.WasteScheduleDto;
import com.gdsc.CGLH.service.JwtTokenBlacklistService;
import com.gdsc.CGLH.service.WasteService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

import static com.gdsc.CGLH.config.JwtUtil.extractloginId;
@Slf4j
@RestController
@RequestMapping("/api/waste")
@AllArgsConstructor
public class WasteController {

    private final WasteService wasteService;
    private final JwtTokenBlacklistService blacklistService;

    /**
     * 폐기물 신청 내역 (리스트) (테스트 완료)
     */
    @GetMapping("/list")
    public ResponseEntity<?> getWasteIndex(HttpServletRequest request) {
        log.info("테스트");
        String token = request.getHeader("Authorization");

        if (blacklistService.isTokenBlacklisted(token)) {
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다.");
        }

        String loginId = extractloginId(token);

        List<WasteDto> wasteDtoList = wasteService.getWasteList(loginId);

        return ResponseEntity.ok(wasteDtoList);
    }

    /**
     * 단일 폐기물 신청 자세히 보기 (테스트 완료)
     */
    @GetMapping("/{wasteId}")
    public ResponseEntity<?> getWaste(@PathVariable("wasteId") Long wasteId) {
        return ResponseEntity.ok(wasteService.getWaste(wasteId));
    }

    /**
     * 파쇄 신청 추가 (테스트 완료)
     */
    @PostMapping
    public ResponseEntity<?> saveWaste(HttpServletRequest request, @RequestBody RequestWaste requestWaste) throws IOException {
        // jwt 인증 로그인정보 가져오는 방법 1번
        String token = request.getHeader("Authorization");

        if (blacklistService.isTokenBlacklisted(token)) {
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다.");
        }

        String loginId = extractloginId(token);

        wasteService.saveWaste(requestWaste, loginId);

        return ResponseEntity.ok(true);
    }

    /**
     * 폐기물 신청 삭제 (테스트 완료)
     */
    @DeleteMapping("/{wasteId}")
    public ResponseEntity<?> deleteWaste(HttpServletRequest request, @PathVariable("wasteId") Long wasteId) {
        String token = request.getHeader("Authorization");

        if (blacklistService.isTokenBlacklisted(token)) {
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다.");
        }

        String loginId = JwtUtil.extractloginId(token);

        if (!loginId.equals(wasteService.findLoginId(wasteId)))
            return new ResponseEntity<>("파쇄신청 작성자와 로그인 정보가 다름.", HttpStatus.FORBIDDEN);

        wasteService.deleteWaste(wasteId);

        return ResponseEntity.ok(true);
    }


    /**
     * 폐기 일정
     * 사용자만? 아니면 해당 센터에 신청한 모든 사용자? (일단 특정 센터에 대한 스케쥴링만 보여줌.)
     * (테스트 완료)
     */
    @GetMapping("/schedule/{centerName}")
    public ResponseEntity<?> schedulingWasteEachCenter(@PathVariable("centerName") String centerName) {
        List<WasteScheduleDto> wasteScheduleDtoList = wasteService.getCenterWastes(centerName);
        return ResponseEntity.ok(wasteScheduleDtoList);
    }


    /**
     * 관리자 승인, 거절
     * STATUS만 변경 -> REFUSE, WAITING, PERMIT
     */
    @PostMapping("/update")
    public ResponseEntity<?> updateWaste(HttpServletRequest request, @RequestBody UpdateWasteDto requestWaste) {
        String token = request.getHeader("Authorization");

        if (blacklistService.isTokenBlacklisted(token)) {
            return ResponseEntity.badRequest().body("토큰이 만료되었습니다.");
        }

        String loginId = extractloginId(token);

        wasteService.updateWaste(requestWaste.getWasteId(), requestWaste.getStatus());

        return ResponseEntity.ok(true);
    }
    @Getter
    static class UpdateWasteDto {
        private Long wasteId;
        private String status;
    }
}
