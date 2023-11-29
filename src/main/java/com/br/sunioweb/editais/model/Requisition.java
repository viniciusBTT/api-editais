package com.br.sunioweb.editais.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
public class Requisition
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codRequisition;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dateRequisition = new Date();

    @ManyToOne(fetch = FetchType.EAGER)
    private Requester requester;

    @ManyToOne(fetch = FetchType.EAGER)
    private Edital edital;

    public Requisition(){}

    public Requisition(Requester requester, Edital edital)
    {
        this.requester = requester;
        this.edital = edital;
    }
}