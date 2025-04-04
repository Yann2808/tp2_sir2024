package dto;

import entity.TicketStatut;

import java.time.LocalDateTime;

public class TicketDTO {
    private Long id;
    private Long evenementId;
    private Long acheteurId;
    private String dateCreation;
    private String dateAchat;
    private Double prix;
    private int ticketRestant;
    private TicketStatut statut;
    private String qrCode;

    public TicketDTO() {
    }

    public TicketDTO(
            Long id, Long evenementId, Long acheteurId, String dateAchat, String dateCreation,
            Double prix, int ticketRestant, String statut, String qrCode
    ) {
        this.id = id;
        this.evenementId = evenementId;
        this.acheteurId = acheteurId;
        this.dateAchat = dateAchat;
        this.dateCreation = dateCreation;
        this.prix = prix;
        this.ticketRestant = ticketRestant;
        this.statut = TicketStatut.valueOf(statut);
        this.qrCode = qrCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEvenementId() {
        return evenementId;
    }

    public void setEvenementId(Long evenementId) {
        this.evenementId = evenementId;
    }

    public Long getAcheteurId() {
        return acheteurId;
    }

    public void setAcheteurId(Long acheteurId) {
        this.acheteurId = acheteurId;
    }

    public LocalDateTime getDateCreation() {
        return LocalDateTime.parse(dateCreation);
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateAchat() {
        return LocalDateTime.parse(dateAchat);
    }

    public void setDateAchat(String dateAchat) {
        this.dateAchat = dateAchat;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public int getTicketRestant() {
        return ticketRestant;
    }

    public void setTicketRestant(int ticketRestant) {
        this.ticketRestant = ticketRestant;
    }

    public TicketStatut getStatut() {
        return statut;
    }

    public void setStatut(TicketStatut statut) {
        this.statut = statut;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}