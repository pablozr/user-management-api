package com.bagaggio.user_management.dto.user;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateProfileDTO {
    @Nullable
    private String nomeCompleto;
    @Nullable
    private String bio;
    @Nullable
    @Email(message = "Formato de email invalido")
    private String email;
    @Nullable
    private String dataNascimento;
}
