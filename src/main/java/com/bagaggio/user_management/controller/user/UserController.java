package com.bagaggio.user_management.controller.user;

import com.bagaggio.user_management.dto.apiResponse.ApiResponseDTO;
import com.bagaggio.user_management.dto.user.UserProfileDTO;
import com.bagaggio.user_management.dto.user.UserUpdateProfileDTO;
import com.bagaggio.user_management.model.User;
import com.bagaggio.user_management.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponseDTO<UserProfileDTO>> getProfile(){
        UserProfileDTO userProfile = userService.getSelfUserProfile();
        ApiResponseDTO<UserProfileDTO> response = ApiResponseDTO.success(userProfile,"Perfil recuperado com sucesso.");
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/me")
    public ResponseEntity<ApiResponseDTO<UserProfileDTO>> updateProfile(@Valid @RequestBody UserUpdateProfileDTO userProfileDTO, HttpServletRequest request) {
        UserProfileDTO updatedProfile = userService.updateSelfUserProfile(userProfileDTO,request);
        ApiResponseDTO<UserProfileDTO> response = ApiResponseDTO.success(updatedProfile, "Perfil atualizado com sucesso.");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/me")
    public ResponseEntity<ApiResponseDTO<Object>> deleteProfile() {
        userService.deleteSelfUserProfile();
        ApiResponseDTO<Object> response = ApiResponseDTO.success(null, "Perfil deletado com sucesso.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponseDTO<User>> getUserProfileById(@PathVariable Long id){
        User user = userService.getUserById(id);
        ApiResponseDTO<User> response = ApiResponseDTO.success(user,"Usuario recuperado com sucesso.");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponseDTO<Object>> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        ApiResponseDTO<Object> response = ApiResponseDTO.success(null, "Usuario deletado com sucesso.");
        return ResponseEntity.ok(response);
    }
}
