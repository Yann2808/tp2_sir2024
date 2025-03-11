package entity;

public enum TicketStatut {
    PAYE,   // Ticket payé, prêt à être utilisé
    UTILISE,    // Ticket validé (utilisé à l'événement)
    ANNULE,     // Ticket annulé
    EN_ATTENTE  // Ticket en attente de paiement (optionnel)
}
