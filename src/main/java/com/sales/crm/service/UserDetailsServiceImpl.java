package com.sales.crm.service;

import com.sales.crm.model.User;
import com.sales.crm.exceptions.ResourceNotFoundException;
import com.sales.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByName(username).orElseThrow(() -> new ResourceNotFoundException("user not found"));

        //imi creez un user pentru spring security, care accepta ca parametrii un username, o parole si o lista de SimpleGrantedAuth..
        // (in cazul nostru o lista de roluri, pe care o optinem printr-o metoda)
        return new org.springframework.security.core.userdetails.User (user.getName(), user.getPass(), buildSimpleGrantedAuthorities(user));

    }

    @Transactional
    public List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(User user){
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleType().name())) //de aici luam setul de roluri si il colectam intr-o lista
                .collect(Collectors.toList());
    }



}
