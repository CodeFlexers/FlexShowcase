package com.codeflexers.flexshowcaseback.mypage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "MyPageUserInfor")
@Table(name = "user_infor")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class MyPageUserInfor {
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

    @JoinColumn(name = "user_code")
    @OneToOne
    private MyPageUser user;
}
