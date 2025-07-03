package org.example.kaamelott.dto;

import java.util.List;

public class RapportActiviteMensuelDto {
    private int nbQuetesInitiees;
    private int nbQuetesTerminees;
    private int nbChevaliersParticipants;
    private String queteLaPlusRatee;
    private List<String> chevaliersResponsables;

    public int getNbQuetesInitiees() { return nbQuetesInitiees; }
    public void setNbQuetesInitiees(int nbQuetesInitiees) { this.nbQuetesInitiees = nbQuetesInitiees; }

    public int getNbQuetesTerminees() { return nbQuetesTerminees; }
    public void setNbQuetesTerminees(int nbQuetesTerminees) { this.nbQuetesTerminees = nbQuetesTerminees; }

    public int getNbChevaliersParticipants() { return nbChevaliersParticipants; }
    public void setNbChevaliersParticipants(int nbChevaliersParticipants) { this.nbChevaliersParticipants = nbChevaliersParticipants; }

    public String getQueteLaPlusRatee() { return queteLaPlusRatee; }
    public void setQueteLaPlusRatee(String queteLaPlusRatee) { this.queteLaPlusRatee = queteLaPlusRatee; }

    public List<String> getChevaliersResponsables() { return chevaliersResponsables; }
    public void setChevaliersResponsables(List<String> chevaliersResponsables) { this.chevaliersResponsables = chevaliersResponsables; }
}