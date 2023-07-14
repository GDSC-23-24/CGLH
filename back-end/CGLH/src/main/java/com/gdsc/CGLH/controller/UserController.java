package com.gdsc.CGLH.controller;

import com.gdsc.CGLH.dto.UserDto;
import com.gdsc.CGLH.dto.UserJoinFormDto;
import com.gdsc.CGLH.dto.role.Role;
import com.gdsc.CGLH.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;


    /**
     *  회원가입
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserJoinFormDto user) {
        try {
            userService.register(user);
            return ResponseEntity.ok("사용자 등록이 완료되었습니다!");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto, HttpSession session){
        try{
            UserDto loginUser = userService.login(userLoginDto.getLoginId(), userLoginDto.getPassword());
            session.setAttribute("user", loginUser);
            return ResponseEntity.ok("로그인");
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }

    }

    /**
     *  로그아웃
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("로그아웃");
    }


    /**
     * 아이디 중복확인
     */
    @PostMapping("validate-id")
    public ResponseEntity<?> validationId(@RequestBody UserLoginDto user){
        Boolean isValid = userService.validateLoginId(user.getLoginId());
        if(!isValid) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(true);
    }


    /**
     *      후에 세션은 이런 식으로 로그인한 사용자만 사용할 수 있는 기능을 쓸 때 사용한다.
     *
     *     @GetMapping
     *     public ResponseEntity<?> getProfile(HttpSession session) {
     *         UserDto user = (UserDto) session.getAttribute("user");
     *         if (user == null) {
     *             return new ResponseEntity<>("No user logged in", HttpStatus.UNAUTHORIZED);
     *         } else {
     *             return ResponseEntity.ok(user);
     *         }
     *     }
     */


    @Getter
    @AllArgsConstructor
    private class UserLoginDto{
        private String loginId;
        private String password;
    }






}
