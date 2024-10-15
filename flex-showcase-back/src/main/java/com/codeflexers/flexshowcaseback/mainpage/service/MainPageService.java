package com.codeflexers.flexshowcaseback.mainpage.service;

import com.codeflexers.flexshowcaseback.mainpage.entity.UserInfor;
import com.codeflexers.flexshowcaseback.mainpage.repository.MainPageUserInforRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainPageService {
    private final MainPageUserInforRepository mainPageUserInforRepository;
    public MainPageService(MainPageUserInforRepository mainPageUserInforRepository) {
        this.mainPageUserInforRepository = mainPageUserInforRepository;
    }

    public List<UserInfor> getUserCard() {
        return mainPageUserInforRepository.getAllUserCard();
    }
}
