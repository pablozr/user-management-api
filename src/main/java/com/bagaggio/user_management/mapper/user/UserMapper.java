package com.bagaggio.user_management.mapper.user;

import com.bagaggio.user_management.dto.user.UserProfileDTO;
import com.bagaggio.user_management.dto.user.UserRegisterDTO;
import com.bagaggio.user_management.dto.user.UserResponseDTO;
import com.bagaggio.user_management.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDTO toResponseDTO(User user){
        if (user == null) return null;

        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setUsername(user.getUsername());
        responseDTO.setEmail(user.getEmail());
        return responseDTO;
    }

    public User toEntity(UserRegisterDTO registerDTO){
        if (registerDTO == null) return null;

        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setUsername(registerDTO.getUsername());
        user.setPassword(registerDTO.getPassword());
        return user;
    }

    public UserProfileDTO toProfileDTO(User user){
        if (user == null) return null;
        UserProfileDTO profileDTO = new UserProfileDTO();
        profileDTO.setId(user.getId());
        profileDTO.setEmail(user.getEmail());
        profileDTO.setBio(user.getBio());
        profileDTO.setNomeCompleto(user.getNomeCompleto());
        profileDTO.setDataNascimento(user.getDataNascimento());
        return profileDTO;
    }
}
