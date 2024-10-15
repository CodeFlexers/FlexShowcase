package com.codeflexers.flexshowcaseback.mainpage.repository;

import com.codeflexers.flexshowcaseback.mainpage.entity.UserInfor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MainPageUserInforRepository extends JpaRepository<UserInfor, Long> {

    @Query("SELECT u FROM MainPageUserInfor u")
    List<UserInfor> getAllUserCard();
}
