package dto;

public class TicketPurchaseDTO {
    private Long evenementId;
    private Long acheteurId;
    private int nbreTicketVoulu;

    // Getters et setters
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

    public int getNbreTicketVoulu() {
        return nbreTicketVoulu;
    }

    public void setNbreTicketVoulu(int nbreTicketVoulu) {
        this.nbreTicketVoulu = nbreTicketVoulu;
    }
}

