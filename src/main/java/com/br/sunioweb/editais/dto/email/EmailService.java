package com.br.sunioweb.editais.dto.email;

import com.br.sunioweb.editais.ultil.EmailDetails;

public interface EmailService {

    String sendSimpleMail(EmailDetails details);

    String sendMailWithAttachment(EmailDetails details);
}