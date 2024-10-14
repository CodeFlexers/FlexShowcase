package com.codeflexers.flexshowcaseback.security.service;

import com.codeflexers.flexshowcaseback.security.dto.CustomUserDetails;
import com.codeflexers.flexshowcaseback.security.entity.User;
import com.codeflexers.flexshowcaseback.security.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userData = userRepository.findByUserId(username);
        if(userData != null){
            return new CustomUserDetails(userData);
        }

        return null;
    }
}
