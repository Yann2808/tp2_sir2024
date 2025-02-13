package service;

import dao.EvenementDao;
import dao.OrganisateurDao;
import entity.Evenement;
import entity.Organisateur;

import java.util.List;

public class EvenementService {

    private EvenementDao evenementDao;
    private OrganisateurDao organisateurDao;

    public EvenementService() {
        this.evenementDao = new EvenementDao();
        this.organisateurDao = new OrganisateurDao();
    }

    public EvenementService(EvenementDao evenementDao, OrganisateurDao organisateurDao) {
        this.evenementDao = evenementDao;
        this.organisateurDao = organisateurDao;
    }

    public Evenement createEvenement(Evenement evenement, Long organisateurId) {
        Organisateur organisateur = organisateurDao.findOne(organisateurId);
        if (organisateurId == null) {
            throw new RuntimeException("Organisateur non trouvé");
        }
        evenement.setOrganisateur(organisateur);
        evenementDao.save(evenement);
        return evenement;
    }

    public List<Evenement> getEvenementsByOrganisateur(Long organisateur_id) {
        Organisateur organisateur = organisateurDao.findOne(organisateur_id);
        if (organisateur == null) {
            throw new RuntimeException("Organisateur non trouvé");
        }
        return evenementDao.findEvenementByOrganisateur(organisateur);
    }

}
