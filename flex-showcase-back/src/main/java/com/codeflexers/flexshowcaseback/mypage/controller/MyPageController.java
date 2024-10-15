package com.codeflexers.flexshowcaseback.mypage.controller;

import com.codeflexers.flexshowcaseback.mypage.dto.MyPageDTO;
import com.codeflexers.flexshowcaseback.mypage.dto.MyPageModifyDTO;
import com.codeflexers.flexshowcaseback.mypage.service.MyPageService;
import com.codeflexers.flexshowcaseback.security.dto.CustomUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MyPageController {
    @Value("${file.download-url}")
    private String url;
    private final MyPageService myPageService;
    public MyPageController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }
    @GetMapping("/my-page")
    public ResponseEntity<?> getMyPageUserInformation(@AuthenticationPrincipal CustomUserDetails user){
        try {
            MyPageDTO myPageDTO = myPageService.getMyPageUserInformation(user);
            myPageDTO.setProfileImage(url+myPageDTO.getProfileImage());
            return ResponseEntity.ok().body(myPageDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/my-page")
    public ResponseEntity<?> modifyUserInformation(MyPageModifyDTO modifyContent, @AuthenticationPrincipal CustomUserDetails user){
        try{
            MyPageDTO myPageDTO = myPageService.ModifyUserInformation(modifyContent, user.getUserCode());
            myPageDTO.setProfileImage(url+myPageDTO.getProfileImage());
            return ResponseEntity.ok().body(myPageDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/my-page/profile")
    public ResponseEntity<String> modifyProfileImage(@RequestParam MultipartFile file, @AuthenticationPrincipal CustomUserDetails user){
        System.out.println("??");
        String fileName = myPageService.modifyProfileImage(file,user.getUserCode());
        if(fileName != null){
            return ResponseEntity.ok().body(url+fileName);
        } else {
            return ResponseEntity.badRequest().body("프로필 사진이 정상적으로 등록되지 않았습니다.");
        }
    }

    @PostMapping("/my-page/password")
    public ResponseEntity<?> modifyPassword(String password, @AuthenticationPrincipal CustomUserDetails user){
        try {
            myPageService.modifyPassword(password,user.getUserCode());
            return ResponseEntity.ok().body("비밀번호가 변경되었습니다.");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
