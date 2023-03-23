package com.ll.sbr.user.service;

import com.ll.sbr.user.model.SiteUser;
import com.ll.sbr.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public SiteUser getSiteUser(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }

    @Transactional
    public SiteUser create(String uesrsname, String email, String password) {
        return userRepository.save(SiteUser.builder()
                .username(uesrsname)
                .email(email)
                .password(password) // required encode
                .build());
    }
}
