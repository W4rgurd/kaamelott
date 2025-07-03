package org.example.kaamelott.controller;

import org.example.kaamelott.dto.RapportActiviteMensuelDto;
import org.example.kaamelott.services.StatistiquesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatistiquesController {
    private final StatistiquesService statistiquesService;

    public StatistiquesController(StatistiquesService statistiquesService) {
        this.statistiquesService = statistiquesService;
    }

    @GetMapping("/stats/rapport-activite-mensuel")
    public RapportActiviteMensuelDto getRapportActiviteMensuel(
            @RequestParam int mois,
            @RequestParam int annee) {
        return statistiquesService.getRapportActiviteMensuel(mois, annee);
    }
}