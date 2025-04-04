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
}
