package service;

import dao.EvenementDao;
import dao.OrganisateurDao;
import dto.EvenementDTO;
import entity.Evenement;
import entity.Organisateur;
import jakarta.ws.rs.NotFoundException;
import mapper.EvenementMapper;

import java.util.List;

public class EvenementService {

    private final EvenementDao evenementDao;
    private final OrganisateurDao organisateurDao;
    private final TicketService ticketService;

    public EvenementService() {
        this.evenementDao = new EvenementDao();
        this.organisateurDao = new OrganisateurDao();
        this.ticketService = new TicketService();
    }

    public EvenementService(EvenementDao evenementDao, OrganisateurDao organisateurDao, TicketService ticketService) {
        this.evenementDao = evenementDao;
        this.organisateurDao = organisateurDao;
        this.ticketService = ticketService;
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

        // Générer les tickets
        ticketService.generateTicketsForEvenement(savedEvenementDTO, savedEvenementDTO.getPlacesDisponibles());

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
        Evenement evenement = evenementDao.findEvenementById(id);
        if (evenement == null) {
            throw new RuntimeException("Événement non trouvé");
        }

        return EvenementMapper.toDTO(evenement);
    }

    public EvenementDTO updateEvenement(EvenementDTO evenementDTO) {
        // Validation de l'ID
        if (evenementDTO == null || evenementDTO.getId() == null) {
            throw new IllegalArgumentException("L'ID de l'événement est requis pour la mise à jour.");
        }

        // Recherche de l'événement existant
        Evenement evenement = evenementDao.findOne(evenementDTO.getId());
        if (evenement == null) {
            throw new NotFoundException("Événement avec l'ID " + evenementDTO.getId() + " non trouvé.");
        }

        // Mise à jour des champs (partielle)
        if (evenementDTO.getNom() != null) {
            evenement.setNom(evenementDTO.getNom());
        }
        if (evenementDTO.getDescription() != null) {
            evenement.setDescription(evenementDTO.getDescription());
        }
        if (evenementDTO.getDate() != null) {
            evenement.setDate(evenementDTO.getDate());
        }
        if (evenementDTO.getLieu() != null) {
            evenement.setLieu(evenementDTO.getLieu());
        }
        if (evenementDTO.getPrix() != null) {
            evenement.setPrix(evenementDTO.getPrix());
        }
        if (evenementDTO.getPlacesDisponibles() != null) {
            evenement.setPlacesDisponibles(evenementDTO.getPlacesDisponibles());
        }

        // Persistance de la mise à jour
        Evenement updatedEvenement = evenementDao.update(evenement); // Ici, evenement est bien un Evenement
        return EvenementMapper.toDTO(updatedEvenement);
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