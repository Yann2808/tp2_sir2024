package dao;

import entity.Evenement;
import entity.Organisateur;

import java.util.List;

public class EvenementDao extends AbstractJpaDao<Long, Evenement> {
    public EvenementDao() {
        setClazz(Evenement.class);
    }

    public List<Evenement> findEvenementsByOrganisateurId(Long organisateur_id) {
        return entityManager.createQuery(
                "SELECT e FROM Evenement e WHERE e.organisateur.id = :organisateur_id", Evenement.class)
                .setParameter("organisateur_id", organisateur_id)
                .getResultList();
    }

    public List<Evenement> findEvenementByOrganisateur(Organisateur organisateur) {
        return entityManager.createQuery(
                "SELECT e FROM Evenement e WHERE e.organisateur = :organisateur", Evenement.class)
                .setParameter("organisateur", organisateur)
                .getResultList();
    }

    public List<Evenement> findByPrixLessThan(Double prix) {
        return entityManager.createQuery(
                "SELECT e FROM Evenement  e WHERE e.prix < :prix", Evenement.class)
                .setParameter("prix", prix)
                .getResultList();
    }
}
