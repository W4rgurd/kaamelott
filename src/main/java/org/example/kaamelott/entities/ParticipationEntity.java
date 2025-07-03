// Fichier : src/main/java/org/example/kaamelott/entities/ParticipationEntity.java
package org.example.kaamelott.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "participation_quete")
public class ParticipationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_chevalier", nullable = false)
    private ChevalierEntity chevalier;

    @ManyToOne
    @JoinColumn(name = "id_quete", nullable = false)
    private QueteEntity quete;
    private String role;
    private String statutParticipation;
    private String commentaire_roi;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ChevalierEntity getChevalierId() {
        return chevalier;
    }

    public void setChevalierId(ChevalierEntity chevalier) {
        this.chevalier = chevalier;
    }

    public QueteEntity getQueteId() {
        return quete;
    }

    public void setQueteId(QueteEntity quete) {
        this.quete = quete;
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

    public String getCommentaire_roi() {
        return commentaire_roi;
    }

    public void setCommentaire_roi(String commentaire_roi) {
        this.commentaire_roi = commentaire_roi;
    }

    public ChevalierEntity getChevalier() {
        return chevalier;
    }

    public QueteEntity getQuete() {
        return quete;
    }
}