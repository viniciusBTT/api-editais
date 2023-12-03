package com.br.sunioweb.editais.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Edital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String number;

    @Lob
    @Column(name="description", columnDefinition = "text")
    private String description;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date datePublication;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dateLastUpdate;

    //disponibilidade
    private Boolean disponibility;

    private Boolean visibility;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Edital(){}
    public Edital(long codEdital){
        this.id = codEdital;
    }

    public Edital(String name, String number,String description,Boolean disponibility, Boolean visibility, User user)
    {
        this.name = name;
        this.number = number;
        this.description = description;
        this.visibility = visibility;
        this.disponibility = disponibility;
        this.user = user;
    }
    public Edital(Long id,String name, String number,String description,Boolean disponibility, Boolean visibility, User user, Date datePublication )
    {
        this.id = id;
        this.name = name;
        this.number = number;
        this.description = description;
        this.visibility = visibility;
        this.disponibility = disponibility;
        this.user = user;
        this.datePublication = datePublication;
    }

    @Override
    public String toString() {
        return "Edital{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", description='" + description + '\'' +
                ", datePublication=" + datePublication +
                ", dateLastUpdate=" + dateLastUpdate +
                ", disponibility=" + disponibility +
                ", visibility=" + visibility +
                ", user=" + user +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public Date getDatePublication() {
        return datePublication;
    }
    @Transient
    public String getDatePublicationAsString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return (datePublication != null) ? dateFormat.format(datePublication) : null;
    }

    public Date getDateLastUpdate() {
        return dateLastUpdate;
    }

    public Boolean getDisponibility() {
        return disponibility;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public User getUser() {
        return user;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public void setDateLastUpdate(Date dateLastUpdate) {
        this.dateLastUpdate = dateLastUpdate;
    }

    public void setDisponibility(Boolean disponibility) {
        this.disponibility = disponibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
