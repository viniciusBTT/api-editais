package com.br.sunioweb.editais.ultil;

import lombok.Data;

import java.util.List;

@Data
public class EmailDetails {
    private String recipient;
    private String msgBody;
    private String subject;
    private List<String> attachment;


    public EmailDetails(){}

    public EmailDetails(String recipient, String msgBody, String subject, List<String> attachment)
    {
        this.attachment = attachment;
        this.msgBody = msgBody;
        this.recipient = recipient;
        this.subject = subject;
    }
    public EmailDetails(String recipient, String msgBody, String subject)
    {
        this.msgBody = msgBody;
        this.recipient = recipient;
        this.subject = subject;
    }
}
