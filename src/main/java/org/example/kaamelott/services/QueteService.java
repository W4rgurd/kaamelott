package org.example.kaamelott.services;

import org.example.kaamelott.dto.ParticipantQueteDto;
import org.example.kaamelott.dto.QueteDto;
import org.example.kaamelott.entities.ChevalierEntity;
import org.example.kaamelott.entities.ParticipationEntity;
import org.example.kaamelott.entities.QueteEntity;
import org.example.kaamelott.repositories.ParticipationRepository;
import org.example.kaamelott.repositories.QueteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueteService {
    private final ParticipationRepository participationRepository;
    private final QueteRepository queteRepository;

    public QueteService(ParticipationRepository participationRepository,
                        QueteRepository queteRepository) {
        this.participationRepository = participationRepository;
        this.queteRepository = queteRepository;
    }

    public List<ParticipantQueteDto> getParticipantsByQueteId(Integer queteId) {
        return participationRepository.findByQuete_Id(queteId).stream()
                .map(p -> {
                    ParticipantQueteDto dto = new ParticipantQueteDto();
                    dto.setChevalierId(p.getChevalier().getId());
                    dto.setNom(p.getChevalier().getNom());
                    dto.setTitre(p.getChevalier().getTitre());
                    dto.setRole(p.getRole());
                    dto.setStatutParticipation(p.getStatutParticipation());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public void assignerChevalier(Integer queteId, Integer chevalierId, String role, String statutParticipation) {

        ParticipationEntity participation = new ParticipationEntity();
        participation.setQueteId(new QueteEntity() {{ setId(queteId); }}); // ou récupère l'entité réelle
        participation.setChevalierId(new ChevalierEntity() {{ setId(chevalierId); }});
        participation.setRole(role);
        participation.setStatutParticipation(statutParticipation);

        participationRepository.save(participation);
    }

    public List<QueteDto> getQuetesEnCoursByChevalierId(Integer chevalierId) {
        return participationRepository.findByChevalier_IdAndStatutParticipation(
                        chevalierId, "EN_COURS"
                ).stream()
                .map(p -> {
                    QueteEntity quete = p.getQuete();
                    QueteDto dto = new QueteDto();
                    // Remplir les champs nécessaires du DTO ici
                    // dto.setId(quete.getId());
                    // dto.setNom(quete.getNom());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    public List<QueteDto> getQuetesDifficulteAberranteNonExpirees() {
        LocalDate today = LocalDate.now();
        return queteRepository.findByDifficulteAndDateEcheanceNotDepassee("ABERRANTE", today)
                .stream()
                .map(q -> {
                    QueteDto dto = new QueteDto();
                    dto.setId(q.getId());
                    dto.setNom_quete(q.getNom_quete());
                    dto.setDifficulte(q.getDifficulte());
                    dto.setDate_assignation(q.getDate_assignation());
                    dto.setDate_echeance(q.getDate_echeance());
                    return dto;
                })
                .collect(java.util.stream.Collectors.toList());
    }

    public void retirerChevalierDeQuete(Integer chevalierId, Integer queteId) {
        participationRepository.deleteByChevalier_IdAndQuete_Id(chevalierId, queteId);
    }

    public List<QueteDto> getQuetesEffectifManquant(int minChevaliers) {
        List<Object[]> results = participationRepository.findQuetesWithEffectifManquant(minChevaliers);
        List<Integer> queteIds = results.stream()
                .map(r -> (Integer) r[0])
                .collect(Collectors.toList());
        return queteRepository.findAllById(queteIds).stream()
                .map(q -> {
                    QueteDto dto = new QueteDto();
                    dto.setId(q.getId());
                    dto.setNom_quete(q.getNom_quete());
                    // ... autres champs
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public List<QueteDto> getQuetesLesPlusLongues(int limit) {
        return queteRepository.findQuetesOrderByDureeDesc().stream()
                .limit(limit)
                .map(q -> {
                    QueteDto dto = new QueteDto();
                    dto.setId(q.getId());
                    dto.setNom_quete(q.getNom_quete());
                    dto.setDescription(q.getDescription());
                    dto.setDifficulte(q.getDifficulte());
                    dto.setDate_assignation(q.getDate_assignation());
                    dto.setDate_echeance(q.getDate_echeance());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    public List<QueteDto> getQuetesParPeriode(LocalDate dateDebut, LocalDate dateFin) {
        List<QueteEntity> quetes = queteRepository.findByPeriodeChevauchement(dateDebut, dateFin);
        return quetes.stream().map(q -> {
            QueteDto dto = new QueteDto();
            dto.setNom_quete(q.getNom_quete());
            dto.setDifficulte(q.getDifficulte());
            dto.setDate_assignation(q.getDate_assignation());
            dto.setDate_echeance(q.getDate_echeance());
            // Durée en jours
            dto.setDureeJours((int) java.time.temporal.ChronoUnit.DAYS.between(q.getDate_assignation().toLocalDate(), q.getDate_echeance().toLocalDate()));
            // Nombre de chevaliers
            int nbChevaliers = participationRepository.countByQuete_Id(q.getId());
            dto.setNbChevaliers(nbChevaliers);
            // Statut global
            LocalDate now = LocalDate.now();
            if (now.isBefore(q.getDate_assignation().toLocalDate())) {
                dto.setStatutGlobal("À Venir");
            } else if (now.isAfter(q.getDate_echeance().toLocalDate())) {
                dto.setStatutGlobal("Terminée");
            } else {
                dto.setStatutGlobal("En Cours");
            }
            return dto;
        }).collect(Collectors.toList());
    }
}