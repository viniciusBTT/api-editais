package com.br.sunioweb.editais.repository;

import com.br.sunioweb.editais.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

   UserDetails findBylogin(String login);
}
