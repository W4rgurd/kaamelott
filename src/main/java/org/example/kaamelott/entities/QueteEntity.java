package org.example.kaamelott.entities;


import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "quete")
public class QueteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom_quete;
    private String description;
    private String difficulte;
    private Date date_assignation;
    private Date date_echeance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom_quete() {
        return nom_quete;
    }

    public void setNom_quete(String nom_quete) {
        this.nom_quete = nom_quete;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(String diffuculte) {
        this.difficulte = diffuculte;
    }

    public Date getDate_assignation() {
        return date_assignation;
    }

    public void setDate_assignation(Date date_assignation) {
        this.date_assignation = date_assignation;
    }

    public Date getDate_echeance() {
        return date_echeance;
    }

    public void setDate_echeance(Date date_echeance) {
        this.date_echeance = date_echeance;
    }
}
