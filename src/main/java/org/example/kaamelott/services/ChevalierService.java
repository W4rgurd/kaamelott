package org.example.kaamelott.services;

import org.example.kaamelott.dto.ChevalierDto;
import org.example.kaamelott.dto.ChevalierPerformanceDto;
import org.example.kaamelott.entities.ChevalierEntity;
import org.example.kaamelott.repositories.ChevalierRepository;
import org.example.kaamelott.repositories.ParticipationRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChevalierService {
    private final ChevalierRepository repository;
    private final ParticipationRepository participationRepository;

    public ChevalierService(ChevalierRepository repository,
                            ParticipationRepository participationRepository) {
        this.repository = repository;
        this.participationRepository = participationRepository;
    }

    public List<ChevalierEntity> findAll() {
        return repository.findAll();
    }

    public ChevalierEntity save(ChevalierEntity chevalier) {
        return repository.save(chevalier);
    }


    public List<ChevalierDto> getChevaliersByCaracteristique(String caracteristique) {
        return repository.findByCaracteristiquePrincipale(caracteristique)
                .stream()
                .map(c -> {
                    ChevalierDto dto = new ChevalierDto();
                    dto.setId(c.getId());
                    dto.setNom(c.getNom());
                    dto.setTitre(c.getTitre());
                    dto.setCaracteristiquePrincipale(c.getCaracteristiquePrincipale());
                    dto.setNiveau_bravoure(c.getNiveau_bravoure());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    public ChevalierPerformanceDto getRapportPerformance(Integer chevalierId) {
        int totalTerminees = participationRepository.countByChevalier_IdAndStatutParticipation(chevalierId, "TERMINEE");
        int totalChef = participationRepository.countByChevalier_IdAndRole(chevalierId, "CHEF_EXPEDITION");
        int totalEnCoursOuTerminee = participationRepository.countByChevalier_IdAndStatutParticipationIn(
                chevalierId, List.of("EN_COURS", "TERMINEE")
        );
        double tauxSucces = totalEnCoursOuTerminee > 0 ? (double) totalTerminees / totalEnCoursOuTerminee : 0.0;

        String commentaireFrequent = null;
        List<Object[]> commentaires = participationRepository.findMostFrequentCommentaireRoi(chevalierId);
        if (!commentaires.isEmpty()) {
            commentaireFrequent = (String) commentaires.get(0)[0];
        }

        ChevalierPerformanceDto dto = new ChevalierPerformanceDto();
        dto.setTotalQuetesTerminees(totalTerminees);
        dto.setTotalChefExpedition(totalChef);
        dto.setTauxSucces(tauxSucces);
        dto.setCommentaireRoiFrequent(commentaireFrequent);
        return dto;
    }
}