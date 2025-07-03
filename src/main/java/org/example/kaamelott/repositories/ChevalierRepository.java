package org.example.kaamelott.repositories;

import org.example.kaamelott.entities.ChevalierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChevalierRepository extends JpaRepository<ChevalierEntity, Long> {
    List<ChevalierEntity> findByCaracteristiquePrincipale(String caracteristiquePrincipale);
}
