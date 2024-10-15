package com.codeflexers.flexshowcaseback.mypage.repository;

import com.codeflexers.flexshowcaseback.mypage.entity.MyPageUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyPageUserRepository extends JpaRepository<MyPageUser, Long> {
}
