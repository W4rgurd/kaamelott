package org.example.kaamelott.dto;

public class ParticipantQueteDto {
    private Integer chevalierId;
    private String nom;
    private String titre;
    private String role;
    private String statutParticipation;

    public Integer getChevalierId() {
        return chevalierId;
    }

    public void setChevalierId(Integer chevalierId) {
        this.chevalierId = chevalierId;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatutParticipation() {
        return statutParticipation;
    }

    public void setStatutParticipation(String statutParticipation) {
        this.statutParticipation = statutParticipation;
    }
}