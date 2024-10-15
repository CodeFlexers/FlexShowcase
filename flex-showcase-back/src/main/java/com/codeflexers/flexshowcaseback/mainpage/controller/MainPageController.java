package com.codeflexers.flexshowcaseback.mainpage.controller;

import com.codeflexers.flexshowcaseback.mainpage.service.MainPageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainPageController {
    private final MainPageService mainPageService;
    public MainPageController(MainPageService mainPageService) {
        this.mainPageService = mainPageService;
    }

    @GetMapping("/user-infor")
    public ResponseEntity<?> getUserCard(){
        return ResponseEntity.ok().body(mainPageService.getUserCard());
    }
}
