package com.bagaggio.user_management.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {
    @NotBlank(message = "O nome nao pode ser vazio.")
    @Size(min = 3, max = 25, message = "O username tem que ter entre 3 a 25 caracteres.")
    private String username;

    @NotBlank(message = "O email nao pode ser vazio")
    @Email(message = "Insira um email valido.")
    private String email;

    @NotBlank(message = "A senha nao pode ser vazia.")
    @Size(message = "A senha deve ter no minimo 8 caracteres.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "A senha deve ter letras minusculas, maiusculas e numeros.")
    private String password;

    @Size(max = 250, message = "A bio deve ter no maximo 250 caracteres.")
    private String bio;

    @Size(max = 100, message = "O nome completo deve ter no maximo 100 caracteres.")
    private String nomeCompleto;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
}
