package org.example.kaamelott.controller;

import org.example.kaamelott.dto.ChevalierDto;
import org.example.kaamelott.dto.ChevalierPerformanceDto;
import org.example.kaamelott.dto.QueteDto;
import org.example.kaamelott.entities.ChevalierEntity;
import org.example.kaamelott.services.ChevalierService;
import org.example.kaamelott.services.QueteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chevaliers")
public class ChevalierController {
    private final ChevalierService service;
    private final QueteService queteService;
    private final ChevalierService chevalierService;


    public ChevalierController(ChevalierService service, QueteService queteService, ChevalierService chevalierService) {
        this.service = service;
        this.queteService = queteService;
        this.chevalierService = service;
    }

    @GetMapping
    public List<ChevalierEntity> getAllChevaliers() {
        return service.findAll();
    }

    @PostMapping
    public ChevalierEntity createChevalier(@RequestBody ChevalierEntity chevalier) {
        return service.save(chevalier);
    }

    @GetMapping("/{idChevalier}/quetes-en-cours")
    public List<QueteDto> getQuetesEnCours(
            @PathVariable Integer idChevalier) {
        return queteService.getQuetesEnCoursByChevalierId(idChevalier);
    }

    @GetMapping("/caracteristique/{caracteristique}")
    public List<ChevalierDto> getChevaliersByCaracteristique(@PathVariable String caracteristique) {
        return service.getChevaliersByCaracteristique(caracteristique);
    }


    @GetMapping("/chevaliers/rapport-performance/{idChevalier}")
    public ChevalierPerformanceDto getRapportPerformance(@PathVariable Integer idChevalier) {
        return chevalierService.getRapportPerformance(idChevalier);
    }
}