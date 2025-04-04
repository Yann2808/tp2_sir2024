package mapper;

import dto.EvenementDTO;
import entity.Evenement;

public class EvenementMapper {
    public static EvenementDTO toDTO(Evenement evenement) {
        return new EvenementDTO(
                evenement.getId(),
                evenement.getNom(),
                evenement.getDescription(),
                evenement.getDate(),
                evenement.getLieu(),
                evenement.getPrix(),
                evenement.getPlacesDisponibles(),
                evenement.getOrganisateur() != null ? evenement.getOrganisateur().getId() : null
        );
    }

    public static Evenement toEntity(EvenementDTO evenementdto) {
        Evenement evenement = new Evenement();
        evenement.setId(evenementdto.getId());
        evenement.setNom(evenementdto.getNom());
        evenement.setDescription(evenementdto.getDescription());
        evenement.setDate(evenementdto.getDate());
        evenement.setLieu(evenementdto.getLieu());
        evenement.setPrix(evenementdto.getPrix());
        evenement.setPlacesDisponibles(evenementdto.getPlacesDisponibles());
        // L'organisateur sera géré séparément dans le service
        return evenement;
    }
}
