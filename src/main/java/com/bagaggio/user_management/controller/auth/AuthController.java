package com.bagaggio.user_management.controller.auth;

import com.bagaggio.user_management.dto.apiResponse.ApiResponseDTO;
import com.bagaggio.user_management.dto.login.LoginDTO;
import com.bagaggio.user_management.dto.login.LoginResponseDTO;
import com.bagaggio.user_management.dto.user.UserRegisterDTO;
import com.bagaggio.user_management.dto.user.UserResponseDTO;
import com.bagaggio.user_management.service.AuthService;
import com.bagaggio.user_management.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

   @Autowired
   private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> register (@Valid @RequestBody UserRegisterDTO registerDTO){
        UserResponseDTO createdUser = userService.registerUser(registerDTO);

        ApiResponseDTO<UserResponseDTO> response = ApiResponseDTO.created(createdUser, "Usuario criado com sucesso");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO<LoginResponseDTO>> login(@Valid @RequestBody LoginDTO loginDTO){

        LoginResponseDTO loginResponseDTO = authService.login(loginDTO);
        ApiResponseDTO<LoginResponseDTO> response = ApiResponseDTO.sucess(loginResponseDTO, "Login bem-sucedido");

        return ResponseEntity.ok(response);
    }
}
