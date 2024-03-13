package com.sales.crm.MapperService;

import com.sales.crm.dtos.UserResponseDTO;
import com.sales.crm.model.User;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserMapper {

    public UserResponseDTO mapFromUserToResponseDTO(User user){
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setRoles(user.getRoles().stream()
                        .map(role->role.getRoleType())
                        .collect(Collectors.toList())
                );
        return userResponseDTO;
    }


}
