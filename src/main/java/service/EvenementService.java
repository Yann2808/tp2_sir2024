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
        if (evenementDTO.getOrganisateurId() == null) {
            throw new RuntimeException("L'ID de l'organisateur est obligatoire");
        }

        Organisateur organisateur = organisateurDao.findOne(evenementDTO.getOrganisateurId());

        if (organisateur == null) {
            throw new RuntimeException("Organisateur non trouvé");
        }

        Evenement evenement = EvenementMapper.toEntity(evenementDTO);
        evenement.setOrganisateur(organisateur);

        Evenement savedEvenement = evenementDao.save(evenement);

        EvenementDTO savedEvenementDTO = EvenementMapper.toDTO(savedEvenement);

        return savedEvenementDTO;
    }

    public List<EvenementDTO> getAllEvenements() {
        List<Evenement> evenements = evenementDao.findAllEvenements();

        // Convertir la liste d'entités Evenement en liste de DTO
        return evenements.stream()
                .map(EvenementMapper::toDTO) // Convertit chaque Evenement en EvenementDTO
                .toList(); // Retourne la liste de DTO
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
        EvenementDTO evenementDTO = evenementDao.findOne(id);
        return (evenementDTO != null) ? evenementDTO : null;
    }

    public boolean deleteEvenement(Long id) {
        EvenementDTO evenementDTO = evenementDao.findOne(id);
        if (evenementDTO != null) {
            evenementDao.delete(evenementDTO);
            return true;
        }
        return false;
    }

}