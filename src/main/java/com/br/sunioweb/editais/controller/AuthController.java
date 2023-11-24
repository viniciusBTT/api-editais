package com.br.sunioweb.editais.controller;

import com.br.sunioweb.editais.dto.response.ResponseDTO;
import com.br.sunioweb.editais.dto.user.AuthenticationDTO;
import com.br.sunioweb.editais.dto.user.LoginResponseDTO;
import com.br.sunioweb.editais.dto.user.RegisterDTO;
import com.br.sunioweb.editais.model.User;
import com.br.sunioweb.editais.service.TokenService;
import com.br.sunioweb.editais.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
// Permitir solicitações do seu frontend (localhost)
@CrossOrigin(origins = "http://localhost:3000")
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO data)
    {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(),data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseDTO register(@RequestBody RegisterDTO data)
    {
        if(this.userService.findByLogin(data.login()) != null)
            return new ResponseDTO("Usuario já cadastrado", "406",null);

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());

        this.userService.save(newUser);

        return new ResponseDTO("Usuario cadastrado!","200",null);

    }
}


