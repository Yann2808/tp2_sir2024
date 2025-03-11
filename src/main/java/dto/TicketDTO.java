package dto;

public class TicketDTO {
    private Long id;
    private Long evenementId;
    private Long acheteurId;
    private String dateAchat;
    private float prix;
    private int ticketRestant;
    private String statut;
    private String qrCode;

    public TicketDTO() {
    }

    public TicketDTO(
            Long id, Long evenementId, Long acheteurId, String dateAchat,
            float prix, int ticketRestant, String statut, String qrCode
    ) {
        this.id = id;
        this.evenementId = evenementId;
        this.acheteurId = acheteurId;
        this.dateAchat = dateAchat;
        this.prix = prix;
        this.ticketRestant = ticketRestant;
        this.statut = statut;
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

    public String getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(String dateAchat) {
        this.dateAchat = dateAchat;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getTicketRestant() {
        return ticketRestant;
    }

    public void setTicketRestant(int ticketRestant) {
        this.ticketRestant = ticketRestant;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}