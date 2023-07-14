package com.gdsc.CGLH.controller;


import com.gdsc.CGLH.controller.request.RequestWaste;
import com.gdsc.CGLH.dto.UserDto;
import com.gdsc.CGLH.service.WasteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/api/waste")
@AllArgsConstructor
public class WasteController {

    private final WasteService wasteService ;

    /**
     * 폐기물 신청 내역 (리스트) (미완)
     */
    @GetMapping("/list")
    public ResponseEntity<?> getWasteIndex(HttpSession session){
        /**
         *  구현하기
         */
        return ResponseEntity.ok(true);
    }

    /**
     * 폐기물 신청 내역 자세히 보가 (단일)
     */
    @GetMapping("/{wasteId}")
    public ResponseEntity<?> getWaste(@PathVariable("wasteId") Long wasteId){
        return ResponseEntity.ok(wasteService.getWaste(wasteId));
    }

    /**
     * 폐기물 신청
     */
    @PostMapping
    public ResponseEntity<?> saveWaste(HttpSession session, @RequestBody RequestWaste requestWaste) throws IOException {
        UserDto user = (UserDto) session.getAttribute("user");
        String loginId = user.getLoginId();

        wasteService.saveWaste(requestWaste,loginId);

        return ResponseEntity.ok(true);

    }

    /**
     * 폐기물 신청 삭제
     */
    @DeleteMapping("/{wasteId}")
    public ResponseEntity<?> deleteWaste(HttpSession session, @PathVariable("wasteId") Long wasteId){
        UserDto user = (UserDto) session.getAttribute("user");
        String loginId = user.getLoginId();

        if(! loginId.equals(wasteService.findLoginId(wasteId)))
            return new ResponseEntity<>("파쇄신청 작성자와 로그인 정보가 다름.", HttpStatus.FORBIDDEN);

        wasteService.deleteWaste(wasteId);

        return ResponseEntity.ok(true);
    }


    /**
     * 폐기 일정
     * 사용자만? 아니면 해당 센터에 신청한 모든 사용자?
     */

}
