package com.br.sunioweb.editais.controller;

import com.br.sunioweb.editais.dto.response.ResponseDTO;
import com.br.sunioweb.editais.dto.user.AuthenticationDTO;
import com.br.sunioweb.editais.dto.user.LoginResponseDTO;
import com.br.sunioweb.editais.dto.user.RegisterDTO;
import com.br.sunioweb.editais.model.User;
import com.br.sunioweb.editais.service.TokenService;
import com.br.sunioweb.editais.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;


    @GetMapping
    public List<User> list()
    {
        return userService.listAll();
    }

    /**
     * validando se as credenciais estão corretas
     * @param  data(password, login)
     * @return
     */
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody AuthenticationDTO data)
    {
        try
        {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(),data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());
            User user = userService.findByLogin(data.login());

            return new LoginResponseDTO("Sucesso ao logar","200",token,user);
        } catch (BadCredentialsException e) {
             return new LoginResponseDTO("Usuário e/ou Senha incorreto","401",null,null);
        }

    }

    /**
     *
     * @param data (login, password e role)
     * @return se o usuario foi cadastrado ou não
     */
    @PostMapping("/register")
    public ResponseDTO register(@RequestBody RegisterDTO data)
    {
        if(this.userService.findByLogin(data.login()) != null)
            return new ResponseDTO("Usuário já cadastrado", "406",null);

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.userService.save(newUser);

        return new ResponseDTO("Usuário cadastrado!","200",null);

    }

    /**
     * Função para validar se o token do usuario está valido.
     * @return o usuarios autenticado ou null
     */
    @GetMapping("/validate")
    public ResponseDTO validate(Long id)
    {
        return new ResponseDTO("Autenticado","200",userService.findById(id));
    }
}


