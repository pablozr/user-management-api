package com.bagaggio.user_management.service;

import com.bagaggio.user_management.dto.user.UserProfileDTO;
import com.bagaggio.user_management.dto.user.UserRegisterDTO;
import com.bagaggio.user_management.dto.user.UserResponseDTO;
import com.bagaggio.user_management.exception.UserJaExistenteException;
import com.bagaggio.user_management.mapper.user.UserMapper;
import com.bagaggio.user_management.model.User;
import com.bagaggio.user_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    public UserResponseDTO registerUser (UserRegisterDTO registerDTO){
        if (userRepository.findByUsername(registerDTO.getUsername()).isPresent()){
            throw new UserJaExistenteException("Ja existe um usuario com esse username cadastrado");
        }
        User user = userMapper.toEntity(registerDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        return userMapper.toResponseDTO(savedUser);
    }

    public UserProfileDTO getUserProfile(){
        User currentUser = getAuthenticatedUser();
        return userMapper.toProfileDTO(currentUser);
    }

    public User getAuthenticatedUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        } else {
            throw new IllegalStateException("O principal de autenticação não é uma instância de User.");
        }
    }
}
