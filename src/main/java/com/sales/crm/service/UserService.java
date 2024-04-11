package com.sales.crm.service;

import com.sales.crm.MapperService.UserMapper;
import com.sales.crm.dtos.AuthRequestDTO;
import com.sales.crm.dtos.UserResponseDTO;
import com.sales.crm.model.Category;
import com.sales.crm.model.Role;
import com.sales.crm.model.RoleType;
import com.sales.crm.model.User;
import com.sales.crm.exceptions.ResourceNotFoundException;
import com.sales.crm.repository.RoleRepository;
import com.sales.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {


    private AuthenticationManager authenticationManager;

    private JwtTokenService jwtTokenService;

    private UserDetailsServiceImpl userDetailsService;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private RoleRepository roleRepository;
    private UserMapper userMapper;

    @Autowired
    public UserService(AuthenticationManager authenticationManager, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRepository userRepository, JwtTokenService jwtTokenService, UserDetailsServiceImpl userDetailsService, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userMapper=userMapper;
    }

    public String authenticate (AuthRequestDTO authRequestDTO){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequestDTO.getUsername());
        return jwtTokenService.generateToken(userDetails);
    }

    @Transactional
    public UserResponseDTO register (AuthRequestDTO authRequestDTO){
        Optional<User> userOptional = userRepository.findUserByName(authRequestDTO.getUsername());

        if (userOptional.isPresent()){
            throw new ResourceNotFoundException("already exists");
        }
        User user = new User();
        user.setName(authRequestDTO.getUsername());
        user.setActive(true);
        user.setPass(passwordEncoder.encode(authRequestDTO.getPassword()));
        user.setEmail(authRequestDTO.getEmail());
        Role role = roleRepository.findByRoleType(RoleType.ROLE_SALES).orElseThrow(()->new ResourceNotFoundException("role not found"));
        user.getRoles().add(role);
        role.getUsers().add(user);
        userRepository.save(user);
        return userMapper.mapFromUserToResponseDTO(user);

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
    @Transactional
    public User findLoggedInUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User foundUser = userRepository.findUserByName(userDetails.getUsername()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        return foundUser;
    }
    @Transactional
    public List<UserResponseDTO> findAllUsers (){
        List<User> foundUsers = userRepository.findAll();
        List<UserResponseDTO> responseUsers = foundUsers.stream()
                .map(user->userMapper.mapFromUserToResponseDTO(user))
                .collect(Collectors.toList());
        return responseUsers;
    }
    @Transactional
    public String changeActiveStatus(Long userId, Boolean status){
        User user = userRepository.findUserById(userId).orElseThrow(()->new ResourceNotFoundException("User not found"));
        user.setActive(status);
        userRepository.save(user);
        if(status){
            return "User is ACTIVE now";
        }else
            return "User is INACTIVE now";

    }
    @Transactional
    public String deleteUserById(Long userId){
        User user = userRepository.findUserById(userId).orElseThrow(()->new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
        return "User has been succesfully deleted";
    }


}
