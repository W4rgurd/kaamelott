package org.example.kaamelott.dto;

public class AssignChevalierRequest {
    private Integer chevalierId;
    private String role;
    private String statutParticipation;

    public Integer getChevalierId() { return chevalierId; }
    public void setChevalierId(Integer chevalierId) { this.chevalierId = chevalierId; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getStatutParticipation() { return statutParticipation; }
    public void setStatutParticipation(String statutParticipation) { this.statutParticipation = statutParticipation; }
}