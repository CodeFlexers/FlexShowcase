package com.codeflexers.flexshowcaseback.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class JoinDTO {
    private String userId;
    private String userPw;
    private String userNickname;
}
