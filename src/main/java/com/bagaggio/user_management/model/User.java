package com.bagaggio.user_management.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true, length = 500)
    private String bio;

    @Column(nullable = true)
    private String NomeCompleto;

    @Column(nullable = true)
    private LocalDate dataNascimento;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Se você tiver roles/perfis, a lógica para retorná-los entraria aqui.
        // Por enquanto, podemos retornar uma lista vazia.
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        // ESSENCIAL: Defina qual campo representa o "username" para o Spring.
        // Como sua autenticação é por email, retornamos o email.
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
