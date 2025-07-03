package org.example.kaamelott.repositories;

import org.example.kaamelott.entities.QueteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface QueteRepository extends JpaRepository<QueteEntity, Integer> {
    @Query("SELECT q FROM QueteEntity q WHERE q.difficulte = :difficulte AND (q.date_echeance IS NULL OR q.date_echeance >= :today)")
    List<QueteEntity> findByDifficulteAndDateEcheanceNotDepassee(String difficulte, LocalDate today);

    @Query("SELECT q FROM QueteEntity q ORDER BY (q.date_echeance - q.date_assignation) DESC")
    List<QueteEntity> findQuetesOrderByDureeDesc();

    @Query("SELECT q FROM QueteEntity q WHERE q.date_assignation <= :dateFin AND q.date_echeance >= :dateDebut")
    List<QueteEntity> findByPeriodeChevauchement(LocalDate dateDebut, LocalDate dateFin);

    @Query("SELECT COUNT(q) FROM QueteEntity q WHERE MONTH(q.date_assignation) = :mois AND YEAR(q.date_assignation) = :annee")
    int countQuetesInitieesParMois(@Param("mois") int mois, @Param("annee") int annee);

    @Query("SELECT COUNT(q) FROM QueteEntity q WHERE MONTH(q.date_echeance) = :mois AND YEAR(q.date_echeance) = :annee AND q.date_echeance < CURRENT_DATE")
    int countQuetesTermineesParMois(@Param("mois") int mois, @Param("annee") int annee);
}