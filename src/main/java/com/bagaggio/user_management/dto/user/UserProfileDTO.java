package com.bagaggio.user_management.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDTO {
    private Long id;
    private String nomeCompleto;
    private String email;
    private LocalDate dataNascimento;
    private String bio;
}
