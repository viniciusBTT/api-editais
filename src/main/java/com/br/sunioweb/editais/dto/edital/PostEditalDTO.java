package com.br.sunioweb.editais.dto.edital;

import java.util.Date;

public record PostEditalDTO(String name,
                            String number,
                            String description,
                            Boolean disponibility,
                            Boolean visibility,
                            Long userId
                            ) {}
