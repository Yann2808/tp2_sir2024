package entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;
    @Column(name = "date_achat")
    private LocalDateTime dateAchat;
    @Column(name="prix")
    private Double prix;
    @Column(name = "nbre_ticket") // Spécifie le nom exact de la colonne
    private int nbreTicket;

    @Enumerated(EnumType.STRING)    // Pour sauver l'enum comme un String en BD
    private TicketStatut statut;
    private String qrCode;

    @ManyToOne
    @JoinColumn(name = "evenement_id") // Clé étrangère vers Evenement
    private Evenement evenement;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "acheteur_id")   // Clé étrangère vers User (UtilisateurParticulier)
    private UtilisateurParticulier acheteur;

    public Ticket(Long id, Long evenementId, Long acheteurId, String dateAchat, Double prix, int nbreTicket, String statut, String qrCode) {
        this.id = id;
        this.dateAchat = LocalDateTime.parse(dateAchat);
        this.prix = prix;
        this.nbreTicket = nbreTicket;
        this.statut = TicketStatut.valueOf(statut);
        this.qrCode = qrCode;
    }

    public Ticket() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public LocalDateTime getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(LocalDateTime dateAchat) {
        this.dateAchat = dateAchat;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public int getNbreTicket() {
        return nbreTicket;
    }

    public void setNbreTicket(int nbreTicket) {
        this.nbreTicket = nbreTicket;
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

    public UtilisateurParticulier getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(UtilisateurParticulier acheteur) {
        this.acheteur = acheteur;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }



}
