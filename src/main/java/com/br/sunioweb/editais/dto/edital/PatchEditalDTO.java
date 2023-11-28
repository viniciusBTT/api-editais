package com.br.sunioweb.editais.dto.edital;

import java.time.LocalDateTime;
import java.util.Date;

public record PatchEditalDTO(Long id,
                             String name,
                             String number,
                             String description,
                             Date datePublication,
                             Boolean disponibility,
                             Boolean visibility,
                             Long userId) {}
