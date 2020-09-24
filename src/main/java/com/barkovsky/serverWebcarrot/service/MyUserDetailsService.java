package com.barkovsky.serverWebcarrot.service;

import com.barkovsky.serverWebcarrot.model.User;
import com.barkovsky.serverWebcarrot.repository.UserRepository;
import com.barkovsky.serverWebcarrot.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username).get();
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new org.springframework.security.core.userdetails.User (user.getUsername(), user.getPassword(), getAutor(user)) {
        };
    }

    private Set<GrantedAuthority> getAutor (User user) {
        return user.getRoles().stream().map(value -> new SimpleGrantedAuthority(value.getName().getRole())).collect(Collectors.toSet());

    }

}
