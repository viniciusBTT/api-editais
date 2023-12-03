package com.br.sunioweb.editais.dto.user;

import com.br.sunioweb.editais.model.User;

public record LoginResponseDTO(String message, String token, User user) {
}
