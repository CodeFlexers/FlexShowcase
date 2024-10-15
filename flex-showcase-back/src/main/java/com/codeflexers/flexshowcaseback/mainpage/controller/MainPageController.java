package com.codeflexers.flexshowcaseback.mainpage.controller;

import com.codeflexers.flexshowcaseback.mainpage.entity.UserInfor;
import com.codeflexers.flexshowcaseback.mainpage.service.MainPageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/main")
public class MainPageController {
    @Value("${file.download-url}")
    private String url;
    private final MainPageService mainPageService;
    public MainPageController(MainPageService mainPageService) {
        this.mainPageService = mainPageService;
    }

    @GetMapping("/user-infor")
    public ResponseEntity<?> getUserCard(){
        List<UserInfor> users = mainPageService.getUserCard();
        for(UserInfor u : users){
            u.setProfileImage(url+u.getProfileImage());
        }
        return ResponseEntity.ok().body(users);
    }
}
