package com.codeflexers.flexshowcaseback.mypage.repository;

import com.codeflexers.flexshowcaseback.mypage.dto.MyPageDTO;
import com.codeflexers.flexshowcaseback.mypage.entity.MyPageUserInfor;
import com.codeflexers.flexshowcaseback.security.dto.CustomUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MyPageUserInforRepository extends JpaRepository<MyPageUserInfor, Long> {
    @Query("SELECT new com.codeflexers.flexshowcaseback.mypage.dto.MyPageDTO(" +
            "t.user.userCode," +
            "t.user.userId," +
            "t.user.userRole," +
            "t.user.userNickname," +
            "t.profileImage," +
            "t.contentHTML," +
            "t.email," +
            "t.phone," +
            "t.name," +
            "t.birthDate) FROM MyPageUserInfor t " +
            "WHERE t.user.userCode = :user")
    MyPageDTO getMyPageUserInformation(Long user);
    MyPageUserInfor findByUser_userCode(Long code);
}
