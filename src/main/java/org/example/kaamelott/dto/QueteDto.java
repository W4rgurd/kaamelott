package org.example.kaamelott.dto;


import java.sql.Date;

public class QueteDto {
    private Integer id;
    private String nom_quete;
    private String description;
    private String difficulte;
    private Date date_assignation;
    private Date date_echeance;

    private  int dureeJours;
    private int nbChevaliers;
    private String statutGlobal;


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

    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
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

    public int getDureeJours() {
        return dureeJours;
    }

    public void setDureeJours(int dureeJours) {
        this.dureeJours = dureeJours;
    }

    public int getNbChevaliers() {
        return nbChevaliers;
    }

    public void setNbChevaliers(int nbChevaliers) {
        this.nbChevaliers = nbChevaliers;
    }

    public String getStatutGlobal() {
        return statutGlobal;
    }

    public void setStatutGlobal(String statutGlobal) {
        this.statutGlobal = statutGlobal;
    }
}
