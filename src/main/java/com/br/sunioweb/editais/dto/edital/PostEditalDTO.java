package com.br.sunioweb.editais.dto.edital;

import java.util.Date;

public record PostEditalDTO(Long id,
                            String name,
                            String number,
                            String description,
                            Boolean disponibility,
                            Boolean visibility,
                            Long userId
                            ) {}
