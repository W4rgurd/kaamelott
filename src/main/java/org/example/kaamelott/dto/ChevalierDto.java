package org.example.kaamelott.dto;

public class ChevalierDto {

    private Integer id;
    private String nom;
    private String titre;
    private String caracteristiquePrincipale;
    private Integer niveau_bravoure;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCaracteristiquePrincipale() {
        return caracteristiquePrincipale;
    }

    public void setCaracteristiquePrincipale(String caracteristiquePrincipale) {
        this.caracteristiquePrincipale = caracteristiquePrincipale;
    }

    public Integer getNiveau_bravoure() {
        return niveau_bravoure;
    }

    public void setNiveau_bravoure(Integer niveau_bravoure) {
        this.niveau_bravoure = niveau_bravoure;
    }
}
