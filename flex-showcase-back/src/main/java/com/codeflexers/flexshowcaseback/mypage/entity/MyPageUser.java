package com.codeflexers.flexshowcaseback.mypage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "MyPageUser")
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class MyPageUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_code")
    private Long userCode;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_pw")
    private String userPw;
    @Column(name = "user_role")
    private String userRole;
    @Column(name = "user_nickname")
    private String userNickname;

    public MyPageUser(Long userCode, String userNickname) {
        this.userCode = userCode;
        this.userNickname = userNickname;
    }
}
