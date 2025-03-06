package dao;

import dto.EvenementDTO;
import entity.Evenement;
import entity.Organisateur;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class EvenementDao extends AbstractJpaDao<Long, EvenementDTO> {
    public EvenementDao() {
        setClazz(EvenementDTO.class);
    }

    public Evenement save(Evenement evenement) {
        EntityTransaction t = this.entityManager.getTransaction();
        t.begin();
        entityManager.persist(evenement);
        t.commit();
        return evenement; // Retourne l'entité enregistrée
    }

    public List<Evenement> findAllEvenements() {
        return entityManager.createQuery(
                "SELECT e FROM Evenement e", Evenement.class)
                .getResultList();
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
