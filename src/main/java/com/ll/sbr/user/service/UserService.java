package com.ll.sbr.user.service;

import com.ll.sbr.user.model.SiteUser;
import com.ll.sbr.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public SiteUser getSiteUser(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }

    @Transactional
    public SiteUser create(String username, String password, String email) {
        return userRepository.save(SiteUser.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build());
    }
}
