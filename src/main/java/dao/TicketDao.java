package dao;

import entity.Ticket;

import java.util.List;

public class TicketDao extends AbstractJpaDao<Long, Ticket> {
    public TicketDao() {
        setClazz(Ticket.class);
    }

    public List<Ticket> findAvailableTicketsForEvenement(Long evenementId) {
        return entityManager.createQuery(
                "SELECT t FROM Ticket t WHERE t.evenement.id = :evenementId  AND t.statut = 'EN_ATTENTE'", Ticket.class)
                .setParameter("evenementId", evenementId)
                .getResultList();
    }

    // Cette méthode utilise un ORM comme Hibernate ou JPA pour récupérer les tickets de l'utilisateur
    public List<Ticket> findByAcheteurId(Long userId) {
        // Implémentation de la requête pour récupérer les tickets en fonction de l'acheteur
        String query = "SELECT t FROM Ticket t WHERE t.acheteur.id = :userId";

        // Code pour exécuter la requête et retourner les résultats
        return entityManager.createQuery(query, Ticket.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
