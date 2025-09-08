package com.bagaggio.user_management.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @NotBlank(message = "Email nao pode ser vazio")
    @Email(message = "Formato de email invalido")
    String email;
    String password;
}
