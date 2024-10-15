package com.codeflexers.flexshowcaseback.mainpage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "MainPageUserInfor")
@Table(name = "user_infor")
@Getter@AllArgsConstructor
@NoArgsConstructor
@Setter
public class UserInfor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_infor_code")
    private Long userInforCode;
    @Column(name = "profile_image")
    private String profileImage;
    @Column(name = "content_html")
    private String contentHTML;
    @Column(name = "name")
    private String name;
    @Column(name = "birthdate")
    private LocalDate birthDate;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;

    //user 엔티티 객체가 필요하지 않으니 코드만 받아오겠습니다.
    @Column(name = "user_code")
    private Long userCode;
}
