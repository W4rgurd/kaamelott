package org.example.kaamelott.repositories;

import org.example.kaamelott.entities.ParticipationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<ParticipationEntity, Integer> {
    List<ParticipationEntity> findByQuete_Id(Integer queteId);
    List<ParticipationEntity> findByChevalier_IdAndStatutParticipation(Integer chevalierId, String statutParticipation);
    void deleteByChevalier_IdAndQuete_Id(Integer chevalierId, Integer queteId);

    int countByQuete_Id(Integer queteId);
    @Query("SELECT p.quete.id, COUNT(p.chevalier.id) FROM ParticipationEntity p GROUP BY p.quete.id HAVING COUNT(p.chevalier.id) < :minChevaliers")
    List<Object[]> findQuetesWithEffectifManquant(@Param("minChevaliers") long minChevaliers);


    int countByChevalier_IdAndStatutParticipation(Integer chevalierId, String statutParticipation);
    int countByChevalier_IdAndRole(Integer chevalierId, String role);
    int countByChevalier_IdAndStatutParticipationIn(Integer chevalierId, List<String> statuts);


    @Query("SELECT p.commentaire_roi, COUNT(p.commentaire_roi) as freq FROM ParticipationEntity p WHERE p.chevalier.id = :chevalierId AND p.commentaire_roi IS NOT NULL GROUP BY p.commentaire_roi ORDER BY freq DESC")
    List<Object[]> findMostFrequentCommentaireRoi(@Param("chevalierId") Integer chevalierId);


    @Query("SELECT DISTINCT p.chevalier.id FROM ParticipationEntity p WHERE MONTH(p.quete.date_assignation) = :mois AND YEAR(p.quete.date_assignation) = :annee")
    List<Integer> findChevaliersParticipantsParMois(@Param("mois") int mois, @Param("annee") int annee);

    @Query("SELECT p.quete.id, COUNT(p) as nbEchecs FROM ParticipationEntity p WHERE p.statutParticipation = 'RATEE' AND MONTH(p.quete.date_echeance) = :mois AND YEAR(p.quete.date_echeance) = :annee GROUP BY p.quete.id ORDER BY nbEchecs DESC")
    List<Object[]> findQueteLaPlusRateeParMois(@Param("mois") int mois, @Param("annee") int annee);

    @Query("SELECT p.chevalier.nom FROM ParticipationEntity p WHERE p.quete.id = :queteId AND p.statutParticipation = 'RATEE'")
    List<String> findChevaliersResponsables(@Param("queteId") Integer queteId);
}


