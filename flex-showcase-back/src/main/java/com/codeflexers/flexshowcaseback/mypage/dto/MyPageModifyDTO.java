package com.codeflexers.flexshowcaseback.mypage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class MyPageModifyDTO {
    private String userNickname;
    private String contentHtml;
    private LocalDate birthDate;
    private String email;
    private String phone;
}
