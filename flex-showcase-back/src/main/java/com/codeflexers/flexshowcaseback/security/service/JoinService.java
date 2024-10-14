package com.codeflexers.flexshowcaseback.security.service;

import com.codeflexers.flexshowcaseback.security.dto.JoinDTO;
import com.codeflexers.flexshowcaseback.security.entity.User;
import com.codeflexers.flexshowcaseback.security.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Transactional
    public void joinUser(JoinDTO joinDTO) throws Exception {
        Boolean isExist = userRepository.existsByUserIdAndUserNickname(joinDTO.getUserId(),joinDTO.getUserNickname());
        if(isExist){    //이미 아이디가 존재한다면?
            throw new Exception("이미 존재하는 아이디,닉네임입니다.");
        }
        //유저 정보가 없다면?
        try{
            User data = new User();
            data.setUserId(joinDTO.getUserId());
            data.setUserNickname(joinDTO.getUserNickname());
            data.setUserPw(bCryptPasswordEncoder.encode(joinDTO.getUserPw()));
            data.setUserRole("ROLE_USER");
            System.out.println(data);
            userRepository.save(data);
        } catch (Exception e){
            throw new Exception("회원가입 중 알 수 없는 에러가 발생했습니다. : "+e.getMessage());
        }
    }
}
