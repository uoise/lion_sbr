package com.ll.sbr.user.service;

import com.ll.sbr.user.model.SiteUser;
import com.ll.sbr.user.model.UserRole;
import com.ll.sbr.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SiteUser siteuser = userRepository.findByUsername(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("user not found");
        });
        Set<GrantedAuthority> authorities = new HashSet<>();
        if ("admin".equals(username)) authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        else authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        return new User(siteuser.getUsername(), siteuser.getPassword(), authorities);
    }
}
