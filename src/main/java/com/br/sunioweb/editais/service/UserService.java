package com.br.sunioweb.editais.service;

import com.br.sunioweb.editais.model.User;
import com.br.sunioweb.editais.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user)
    {
        return userRepository.save(user);
    }

    public User findByLogin(String username){
        return userRepository.findBylogin(username);
    }
    


    public List<User> listAll(){ return userRepository.findAll();}


}
