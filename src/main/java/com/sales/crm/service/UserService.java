package com.sales.crm.service;

import com.sales.crm.dtos.AuthRequestDTO;
import com.sales.crm.model.Role;
import com.sales.crm.model.RoleType;
import com.sales.crm.model.User;
import com.sales.crm.exceptions.ResourceNotFoundException;
import com.sales.crm.repository.RoleRepository;
import com.sales.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {


    private AuthenticationManager authenticationManager;

    private JwtTokenService jwtTokenService;

    private UserDetailsServiceImpl userDetailsService;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private RoleRepository roleRepository;

    @Autowired
    public UserService(AuthenticationManager authenticationManager, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRepository userRepository, JwtTokenService jwtTokenService, UserDetailsServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public String authenticate (AuthRequestDTO authRequestDTO){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequestDTO.getUsername());
        return jwtTokenService.generateToken(userDetails);
    }

    @Transactional
    public User register (AuthRequestDTO authRequestDTO){
        Optional<User> userOptional = userRepository.findUserByName(authRequestDTO.getUsername());

        if (userOptional.isPresent()){
            throw new ResourceNotFoundException("already exists");
        }
        User user = new User();
        user.setName(authRequestDTO.getUsername());
        user.setPass(passwordEncoder.encode(authRequestDTO.getPassword()));
        Role role = roleRepository.findByRoleType(RoleType.ROLE_SALES).orElseThrow(()->new ResourceNotFoundException("role not found"));
        user.getRoles().add(role);
        role.getUsers().add(user);
        return userRepository.save(user);
    }
    @Transactional
    public Role addRoleToUser(Long userId, RoleType roleType){
        User user = userRepository.findUserById(userId).orElseThrow(()->new ResourceNotFoundException("user not found"));
        Role role = roleRepository.findByRoleType(roleType).orElseThrow(()->new ResourceNotFoundException("role not found"));

        // daca rolul exista il adaug la lista de useri si il salvez
        role.getUsers().add(user);
        // Updatez lista de roluri a userului
        user.getRoles().add(role);
        return roleRepository.save(role);
    }
}
