package com.br.sunioweb.editais.dto.user;

import com.br.sunioweb.editais.model.User;

public record LoginResponseDTO(String token, User user) {
}
