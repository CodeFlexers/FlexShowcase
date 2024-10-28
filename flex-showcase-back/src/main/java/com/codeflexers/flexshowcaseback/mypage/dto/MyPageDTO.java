package com.codeflexers.flexshowcaseback.mypage.dto;

import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Setter@Getter@ToString
public class MyPageDTO {
    private Long userCode;
    private String userId;
    private String userRole;
    private String userNickname;
    private String profileImage;
    private String  contentHtml;
    private String email;
    private String phone;
    private String name;
    private LocalDate birthdate;
}
