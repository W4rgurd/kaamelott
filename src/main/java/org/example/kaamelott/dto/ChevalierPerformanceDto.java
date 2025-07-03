package org.example.kaamelott.dto;

public class ChevalierPerformanceDto {
    private int totalQuetesTerminees;
    private int totalChefExpedition;
    private double tauxSucces;
    private String commentaireRoiFrequent;

    public int getTotalQuetesTerminees() { return totalQuetesTerminees; }
    public void setTotalQuetesTerminees(int totalQuetesTerminees) { this.totalQuetesTerminees = totalQuetesTerminees; }

    public int getTotalChefExpedition() { return totalChefExpedition; }
    public void setTotalChefExpedition(int totalChefExpedition) { this.totalChefExpedition = totalChefExpedition; }

    public double getTauxSucces() { return tauxSucces; }
    public void setTauxSucces(double tauxSucces) { this.tauxSucces = tauxSucces; }

    public String getCommentaireRoiFrequent() { return commentaireRoiFrequent; }
    public void setCommentaireRoiFrequent(String commentaireRoiFrequent) { this.commentaireRoiFrequent = commentaireRoiFrequent; }
}