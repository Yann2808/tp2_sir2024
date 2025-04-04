package entity;

public enum TicketStatut {
    EN_ATTENTE, // Ticket en attente de paiement (optionnel)
    PAYE,   // Ticket payé, prêt à être utilisé
    UTILISE,    // Ticket validé (utilisé à l'événement)
    ANNULE,     // Ticket annulé
}
