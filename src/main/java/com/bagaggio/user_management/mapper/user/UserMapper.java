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
        responseDTO.setUsername(user.getDisplayName());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setNomeCompleto(user.getNomeCompleto());
        responseDTO.setBio(user.getBio());
        responseDTO.setDataNascimento(user.getDataNascimento());
        return responseDTO;
    }

    public User toEntity(UserRegisterDTO registerDTO){
        if (registerDTO == null) return null;

        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setDisplayName(registerDTO.getUsername());
        user.setPassword(registerDTO.getPassword());
        user.setNomeCompleto(registerDTO.getNomeCompleto());
        user.setBio(registerDTO.getBio());
        user.setDataNascimento(registerDTO.getDataNascimento());
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
