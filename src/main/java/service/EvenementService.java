package service;

import dao.EvenementDao;
import dao.OrganisateurDao;
import dto.EvenementDTO;
import entity.Evenement;
import entity.Organisateur;
import mapper.EvenementMapper;

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

    public EvenementDTO createEvenement(EvenementDTO evenementDTO) {
        Organisateur organisateur = organisateurDao.findOne(evenementDTO.getOrganisateurId()); // On récupère directement l’ID depuis le DTO

        if (organisateur == null) {
            throw new RuntimeException("Organisateur non trouvé");
        }

        Evenement evenement = EvenementMapper.toEntity(evenementDTO);
        evenement.setOrganisateur(organisateur); // Associe l'organisateur trouvé

        evenementDao.save(evenement); // Sauvegarde l'événement

        return EvenementMapper.toDTO(evenement); // Retourne un DTO au lieu d'une entité
    }


    public List<EvenementDTO> getEvenementsByOrganisateur(Long organisateur_id) {
        Organisateur organisateur = organisateurDao.findOne(organisateur_id);

        if (organisateur == null) {
            throw new RuntimeException("Organisateur non trouvé");
        }

        List<Evenement> evenements = evenementDao.findEvenementByOrganisateur(organisateur);

        // Convertir la liste d'entités Evenement en liste de DTO
        return evenements.stream()
                .map(EvenementMapper::toDTO) // Convertit chaque Evenement en EvenementDTO
                .toList(); // Retourne la liste de DTO
    }

    public EvenementDTO getEvenementById(Long id) {
        Evenement evenement = evenementDao.findOne(id);
        return (evenement != null) ? EvenementMapper.toDTO(evenement) : null;
    }

    public boolean deleteEvenement(Long id) {
        Evenement evenement = evenementDao.findOne(id);
        if (evenement != null) {
            evenementDao.delete(evenement);
            return true;
        }
        return false;
    }

}
