package org.example.kaamelott.services;

import org.example.kaamelott.dto.RapportActiviteMensuelDto;
import org.example.kaamelott.repositories.ParticipationRepository;
import org.example.kaamelott.repositories.QueteRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class StatistiquesService {
    private final QueteRepository queteRepository;
    private final ParticipationRepository participationRepository;

    public StatistiquesService(QueteRepository queteRepository, ParticipationRepository participationRepository) {
        this.queteRepository = queteRepository;
        this.participationRepository = participationRepository;
    }

    public RapportActiviteMensuelDto getRapportActiviteMensuel(int mois, int annee) {
        RapportActiviteMensuelDto dto = new RapportActiviteMensuelDto();
        dto.setNbQuetesInitiees(queteRepository.countQuetesInitieesParMois(mois, annee));
        dto.setNbQuetesTerminees(queteRepository.countQuetesTermineesParMois(mois, annee));
        dto.setNbChevaliersParticipants(participationRepository.findChevaliersParticipantsParMois(mois, annee).size());

        List<Object[]> quetesRatees = participationRepository.findQueteLaPlusRateeParMois(mois, annee);
        if (!quetesRatees.isEmpty()) {
            Integer queteId = (Integer) quetesRatees.get(0)[0];
            String nomQuete = queteRepository.findById(queteId).map(q -> q.getNom_quete()).orElse("Inconnu");
            dto.setQueteLaPlusRatee(nomQuete);
            dto.setChevaliersResponsables(participationRepository.findChevaliersResponsables(queteId));
        } else {
            dto.setQueteLaPlusRatee(null);
            dto.setChevaliersResponsables(Collections.emptyList());
        }
        return dto;
    }
}