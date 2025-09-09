package com.bagaggio.user_management.service;

import com.bagaggio.user_management.dto.user.UserProfileDTO;
import com.bagaggio.user_management.dto.user.UserRegisterDTO;
import com.bagaggio.user_management.dto.user.UserResponseDTO;
import com.bagaggio.user_management.dto.user.UserUpdateProfileDTO;
import com.bagaggio.user_management.exception.EmailJaCadastradoException;
import com.bagaggio.user_management.exception.UserJaExistenteException;
import com.bagaggio.user_management.mapper.user.UserMapper;
import com.bagaggio.user_management.model.User;
import com.bagaggio.user_management.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public UserResponseDTO registerUser (UserRegisterDTO registerDTO){
        if (userRepository.findByDisplayName(registerDTO.getUsername()).isPresent()){
            throw new UserJaExistenteException("Ja existe um usuario com esse username cadastrado");
        }

        if (userRepository.findByEmail(registerDTO.getEmail()).isPresent()){
            throw new EmailJaCadastradoException("Ja existe um usuario com esse email cadastrado");
        }
        User user = userMapper.toEntity(registerDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles("USER_ROLE");

        User savedUser = userRepository.save(user);

        return userMapper.toResponseDTO(savedUser);
    }

    public UserProfileDTO getSelftUserProfile(){
        User currentUser = getAuthenticatedUser();
        return userMapper.toProfileDTO(currentUser);
    }

    @Transactional
    public UserProfileDTO updateSelfUserProfile(UserUpdateProfileDTO profileDTO, HttpServletRequest request){

        User userExists = getAuthenticatedUser();

        if (profileDTO.getNomeCompleto() != null) {
            userExists.setNomeCompleto(profileDTO.getNomeCompleto());
        }
        if (profileDTO.getBio() != null) {
            userExists.setBio(profileDTO.getBio());
        }
        if (profileDTO.getDataNascimento() != null) {
            userExists.setDataNascimento(profileDTO.getDataNascimento());
        }

        boolean emailWasUpdated = false;
        if (profileDTO.getEmail() != null && !profileDTO.getEmail().equals(userExists.getEmail())) {
            if (userRepository.findByEmail(profileDTO.getEmail()).isPresent()) {
                throw new EmailJaCadastradoException("Ja existe um usuario com esse email cadastrado");
            }
            userExists.setEmail(profileDTO.getEmail());
            emailWasUpdated = true;
        }

        User updatedUser = userRepository.save(userExists);

        if(emailWasUpdated){
            request.getSession().invalidate();
            SecurityContextHolder.clearContext();
        }
        return userMapper.toProfileDTO(updatedUser);
    }
    @Transactional
    public void deleteSelfUserProfile(){
        User currentUser = getAuthenticatedUser();
        userRepository.delete(currentUser);
    }

    public User getAuthenticatedUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        } else {
            throw new IllegalStateException("O principal de autenticação não é uma instância de User.");
        }
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("Usuario nao encontrado."));
    }

    public void deleteUserById(Long userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("Usuario nao encontrado."));
        userRepository.delete(user);
    }
}
