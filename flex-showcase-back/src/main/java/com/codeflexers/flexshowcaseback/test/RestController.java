package com.codeflexers.flexshowcaseback.test;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @GetMapping("/test")
    public ResponseEntity<String> testMethod(){

        return ResponseEntity.ok().body("success");
    }
}
