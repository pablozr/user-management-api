package com.bagaggio.user_management.dto.user;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateProfileDTO {
    private String nomeCompleto;
    private String bio;
    @Email(message = "Formato de email invalido")
    private String email;
    private LocalDate dataNascimento;
}
