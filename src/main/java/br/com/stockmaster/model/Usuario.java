package br.com.stockmaster.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Usuario{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_usuario;
    @NotNull
    private String nome;
    @Email
    private String email;
    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, max = 255, message = "Senha deve ter pelo menos 8 caracteres")
    private String senha;
}
