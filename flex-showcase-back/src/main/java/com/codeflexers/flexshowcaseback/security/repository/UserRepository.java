package com.codeflexers.flexshowcaseback.security.repository;

import com.codeflexers.flexshowcaseback.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String username);

    Boolean existsByUserIdAndUserNickname(String userId, String userNickname);
}
