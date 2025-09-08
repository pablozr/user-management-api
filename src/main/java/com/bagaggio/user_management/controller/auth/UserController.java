package com.bagaggio.user_management.controller.auth;

import com.bagaggio.user_management.dto.apiResponse.ApiResponseDTO;
import com.bagaggio.user_management.dto.user.UserProfileDTO;
import com.bagaggio.user_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<ApiResponseDTO<UserProfileDTO>> getProfile(){
        UserProfileDTO userProfile = userService.getUserProfile();
        ApiResponseDTO<UserProfileDTO> response = ApiResponseDTO.sucess(userProfile,"Perfil recuperado com sucesso.");
        return ResponseEntity.ok(response);
    }
}
