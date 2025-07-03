package org.example.kaamelott.controller;

import org.example.kaamelott.dto.AssignChevalierRequest;
import org.example.kaamelott.dto.ParticipantQueteDto;
import org.example.kaamelott.dto.QueteDto;
import org.example.kaamelott.services.QueteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/quetes")
public class QueteController {
    private final QueteService queteService;

    public QueteController(QueteService queteService) {
        this.queteService = queteService;
    }

    @GetMapping("/{id}/participants")
    public List<ParticipantQueteDto> getParticipants(@PathVariable Integer id) {
        return queteService.getParticipantsByQueteId(id);
    }

    @PostMapping("/quetes/{idQuete}/assigner-chevalier")
    public ResponseEntity<Void> assignerChevalier(
            @PathVariable Integer idQuete,
            @RequestBody AssignChevalierRequest request) {
        queteService.assignerChevalier(idQuete, request.getChevalierId(), request.getRole(), request.getStatutParticipation());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/quetes/difficulte-aberrante")
    public List<QueteDto> getQuetesDifficulteAberrante() {
        return queteService.getQuetesDifficulteAberranteNonExpirees();
    }


    @DeleteMapping("/chevaliers/{idChevalier}/retirer-quete/{idQuete}")
    public ResponseEntity<Void> retirerChevalierDeQuete(
            @PathVariable Integer idChevalier,
            @PathVariable Integer idQuete) {
        queteService.retirerChevalierDeQuete(idChevalier, idQuete);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/quetes/effectif-manquant")
    public List<QueteDto> getQuetesEffectifManquant(@RequestParam("minChevaliers") int minChevaliers) {
        return queteService.getQuetesEffectifManquant(minChevaliers);
    }

    @GetMapping("/les-plus-longues")
    public List<QueteDto> getQuetesLesPlusLongues(@RequestParam("limit") int limit) {
        return queteService.getQuetesLesPlusLongues(limit);
    }

    // src/main/java/org/example/kaamelott/controller/QueteController.java
    @GetMapping("/periode")
    public List<QueteDto> getQuetesParPeriode(
            @RequestParam("date_debut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
            @RequestParam("date_fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin) {
        return queteService.getQuetesParPeriode(dateDebut, dateFin);
    }
}