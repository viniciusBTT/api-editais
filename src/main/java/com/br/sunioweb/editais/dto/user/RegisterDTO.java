package com.br.sunioweb.editais.dto.user;


import com.br.sunioweb.editais.ultil.EnumRoleUser;

public record RegisterDTO(String login, String password, EnumRoleUser role) {
}
