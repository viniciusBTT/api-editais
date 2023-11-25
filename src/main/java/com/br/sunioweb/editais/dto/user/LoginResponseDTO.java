package com.br.sunioweb.editais.dto.user;

import com.br.sunioweb.editais.model.User;

public record LoginResponseDTO(String message, String status,String token, User user) {
}
