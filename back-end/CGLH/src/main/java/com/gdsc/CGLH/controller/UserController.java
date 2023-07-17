package com.gdsc.CGLH.controller;

import com.gdsc.CGLH.config.JwtUtil;
import com.gdsc.CGLH.dto.request.UserLoginDto;
import com.gdsc.CGLH.dto.UserDto;
import com.gdsc.CGLH.dto.UserJoinFormDto;
import com.gdsc.CGLH.service.JwtTokenBlacklistService;
import com.gdsc.CGLH.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.gdsc.CGLH.config.JwtUtil.extractloginId;

@Slf4j
@RestController
@RequestMapping("/api/member")
@AllArgsConstructor
@ComponentScan
public class UserController {
    private final UserService userService;
    private final JwtTokenBlacklistService jwtTokenBlacklistService;


    /**
     * 회원가입 (테스트 완료)
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserJoinFormDto user) {

        userService.register(user);
        return ResponseEntity.ok("사용자 등록이 완료되었습니다!");
    }

    /**
     * 로그인 (테스트 완료)
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto) {

        UserDto loginUser = userService.login(userLoginDto.getLoginId(), userLoginDto.getPassword());
        String jwt = JwtUtil.generateToken(loginUser.getLoginId());


        log.info("데이터 확인: " + loginUser.getLoginId());
        return ResponseEntity.ok(jwt);
    }



    /**
     * 로그아웃
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7); // "Bearer " 제거
        if ( token != null)
            jwtTokenBlacklistService.addTokenToBlacklist(token);

        return ResponseEntity.ok("로그아웃");
    }


    /**
     * 아이디 중복확인 (테스트 완료)
     */
    @PostMapping("validate-id")
    public ResponseEntity<?> validationId(@RequestBody UserLoginDto user) {
        Boolean isValid = userService.validateLoginId(user.getLoginId());
        if (!isValid) return ResponseEntity.badRequest().body("중복된 아이디가 존재합니다.");
        return ResponseEntity.ok(true);
    }


/**
 * 후에 세션은 이런 식으로 로그인한 사용자만 사용할 수 있는 기능을 쓸 때 사용한다.
 *
 * @GetMapping public ResponseEntity<?> getProfile(HttpSession session) {
 * UserDto user = (UserDto) session.getAttribute("user");
 * if (user == null) {
 * return new ResponseEntity<>("No user logged in", HttpStatus.UNAUTHORIZED);
 * } else {
 * return ResponseEntity.ok(user);
 * }
 * }
 */





}

