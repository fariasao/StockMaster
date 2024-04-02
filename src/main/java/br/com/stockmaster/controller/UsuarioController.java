package br.com.stockmaster.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.stockmaster.model.Login;
import br.com.stockmaster.model.Usuario;
import br.com.stockmaster.repository.LoginRepository;
import br.com.stockmaster.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("/usuario")
@Slf4j
public class UsuarioController {
    @Autowired
    UsuarioRepository repositoryUsuario;
    @Autowired
    LoginRepository repositoryLogin;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Usuario create(@RequestBody @Valid Usuario usuario) {
        log.info("Cadastrando usuário: {}", usuario);
        repositoryUsuario.save(usuario);
        return usuario;
    }

    @PostMapping("/login")
    public Login login(@RequestBody @Valid Login login){
        log.info("logando usuário: {}", login);
        repositoryLogin.save(login);
        return login;
    }
}
