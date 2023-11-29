package com.br.sunioweb.editais.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Requester
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String nomenclature;

    public Requester(String email, String nomenclature) {
        this.email = email;
        this.nomenclature = nomenclature;
    }
    public Requester(Long id, String email, String nomenclature) {
        this.id = id;
        this.email = email;
        this.nomenclature = nomenclature;
    }
    public Requester() {}

    public void setCodRequester(Long codRequester) {
        this.id = codRequester;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature = nomenclature;
    }

    public Long getCodRequester() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNomenclature() {
        return nomenclature;
    }
}