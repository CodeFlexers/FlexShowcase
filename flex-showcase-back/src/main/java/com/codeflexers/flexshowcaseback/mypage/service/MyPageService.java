package com.codeflexers.flexshowcaseback.mypage.service;

import com.codeflexers.flexshowcaseback.common.Tool;
import com.codeflexers.flexshowcaseback.mypage.dto.MyPageDTO;
import com.codeflexers.flexshowcaseback.mypage.dto.MyPageModifyDTO;
import com.codeflexers.flexshowcaseback.mypage.entity.MyPageUser;
import com.codeflexers.flexshowcaseback.mypage.entity.MyPageUserInfor;
import com.codeflexers.flexshowcaseback.mypage.repository.MyPageUserInforRepository;
import com.codeflexers.flexshowcaseback.mypage.repository.MyPageUserRepository;
import com.codeflexers.flexshowcaseback.security.dto.CustomUserDetails;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MyPageService {
    @Value("${file.upload-dir}")
    private String uploadDir;

    private final MyPageUserInforRepository myPageUserInforRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MyPageUserRepository myPageUserRepository;
    private final Tool tool;

    public MyPageService(MyPageUserInforRepository myPageUserInforRepository, BCryptPasswordEncoder bCryptPasswordEncoder, MyPageUserRepository myPageUserRepository, Tool tool) {
        this.myPageUserInforRepository = myPageUserInforRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.myPageUserRepository = myPageUserRepository;
        this.tool = tool;
    }

    public MyPageDTO getMyPageUserInformation(CustomUserDetails user) {
        return myPageUserInforRepository.getMyPageUserInformation(user.getUserCode());
    }

    @Transactional
    public String modifyProfileImage(MultipartFile file, Long userCode) {
        MyPageUserInfor myPageUserInfor = myPageUserInforRepository.findByUser_userCode(userCode);
        tool.delete(uploadDir+"/"+myPageUserInfor.getProfileImage());
        String fileName = tool.upload(file);
        if(fileName != null){
            myPageUserInfor.setProfileImage(fileName);
            myPageUserInforRepository.save(myPageUserInfor);
            return fileName;
        } else {
            return null;
        }
    }

    @Transactional
    public MyPageDTO ModifyUserInformation(MyPageModifyDTO modifyContent, Long userCode) {
        MyPageUserInfor myPageUserInfor = myPageUserInforRepository.findByUser_userCode(userCode);
        myPageUserInfor.setContentHTML(modifyContent.getContentHtml());
        myPageUserInfor.setBirthDate(modifyContent.getBirthDate());
        myPageUserInfor.setEmail(modifyContent.getEmail());
        myPageUserInfor.setPhone(modifyContent.getPhone());
        myPageUserInfor.setUser(
                new MyPageUser(
                userCode,
                modifyContent.getUserNickname()));
        myPageUserInforRepository.save(myPageUserInfor);
        return myPageUserInforRepository.getMyPageUserInformation(userCode);
    }

    @Transactional
    public void modifyPassword(String password, Long userCode) {
        MyPageUser myPageUser = myPageUserInforRepository.findById(userCode).orElseThrow().getUser();
        myPageUser.setUserPw(bCryptPasswordEncoder.encode(password));
        myPageUserRepository.save(myPageUser);
    }
}
