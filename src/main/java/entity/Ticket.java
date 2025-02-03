package entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateAchat;
    private String statut;
    private String qrCode;

    @ManyToOne
    @JoinColumn(name = "evenement_id") // Clé étrangère vers Evenement
    private Evenement evenement;

    @ManyToOne
    @JoinColumn(name = "acheteur_id")   // Clé étrangère vers User
    private UtilisateurParticulier acheteur;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

}
