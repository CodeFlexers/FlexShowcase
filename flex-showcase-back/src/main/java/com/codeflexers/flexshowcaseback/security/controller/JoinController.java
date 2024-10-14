package com.codeflexers.flexshowcaseback.security.controller;

import com.codeflexers.flexshowcaseback.security.dto.JoinDTO;
import com.codeflexers.flexshowcaseback.security.service.JoinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinController {
    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public ResponseEntity<String> joinUser(JoinDTO joinDTO){
        try {
            joinService.joinUser(joinDTO);
            return ResponseEntity.ok().body("회원가입에 성공했습니다. 로그인 해주세요.");
        } catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("회원 가입에 실패했습니다. : "+e.getMessage());
        }
    }
}
