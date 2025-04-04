package service;

import dao.EvenementDao;
import dao.TicketDao;
import dao.UserDao;
import dao.UtilisateurParticulierDao;
import dto.EvenementDTO;
import dto.TicketDTO;
import entity.Evenement;
import entity.Ticket;
import entity.TicketStatut;
import entity.UtilisateurParticulier;
import mapper.EvenementMapper;
import mapper.TicketMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TicketService {
    private TicketDao ticketDao;
    private EvenementDao evenementDao;
    private UtilisateurParticulierDao utilisateurParticulierDao;
    private UserDao userDao;

    public TicketService() {
        this.ticketDao = new TicketDao();
        this.evenementDao = new EvenementDao();
        this.utilisateurParticulierDao = new UtilisateurParticulierDao();
    }

    public TicketService(EvenementDao evenementDao, UtilisateurParticulierDao utilisateurParticulierDao) {
        this.evenementDao = evenementDao;
        this.utilisateurParticulierDao = utilisateurParticulierDao;
    }

//    public TicketDTO createTicket(TicketDTO ticketDTO) {
//        if (ticketDTO.getEvenementId() == null) {
//            throw new IllegalArgumentException("Le ticket doit être associé à un événement");
//        }
//
//
//    }

    public void generateTicketsForEvenement(EvenementDTO evenementDTO, int numberOfTickets) {

        if (evenementDTO == null) {
            throw new IllegalArgumentException("L'évènement n'existe pas");
        }

        Evenement evenement = EvenementMapper.toEntity(evenementDTO);

        for (int i = 0; i < numberOfTickets; i++) {
            Ticket ticket = new Ticket();
            ticket.setEvenement(evenement);
            ticket.setDateAchat(LocalDateTime.now());
            ticket.setPrix(evenement.getPrix());
            ticket.setNbreTicket(1);
            ticket.setStatut(TicketStatut.EN_ATTENTE);
            ticket.setQrCode(generateQrCode());
            ticket.setAcheteur(null);
            ticketDao.save(ticket);
        }
    }

    private String generateQrCode() {
        return "123-456-789";
    }

    public TicketDTO createTicket(Long evenementId, Long acheteurId, int nbreTicket) {
        if (evenementId == null || acheteurId == null || nbreTicket <= 0) {
            throw new IllegalArgumentException("Les paramètres pour la création de votre ticket sont nuls.");
        }

        Evenement evenement = evenementDao.findEvenementById(evenementId);
        UtilisateurParticulier acheteur = utilisateurParticulierDao.findOne(acheteurId);

        if (evenement == null) {
            throw new IllegalArgumentException("L'évènement n'a pas été trouvé");
        }
        if (acheteur == null) {
            throw new IllegalArgumentException("Acheteur non trouvé");
        }

        //  Vérifier la disponibilité
        List<Ticket> availableTickets = ticketDao.findAvailableTicketsForEvenement(evenementId).stream()
                .filter(ticket -> ticket.getStatut() == TicketStatut.EN_ATTENTE)
                .limit(nbreTicket)
                .toList();

        if (availableTickets.size() < nbreTicket) {
            throw new IllegalArgumentException("Il n'y a pas assez de tickets disponibles");
        }

        // Mettre les tickets à jour après l'achat
        List<TicketDTO> purchasedTickets = new ArrayList<>();
        for (Ticket ticket : availableTickets) {
            ticket.setAcheteur(acheteur);
            ticket.setStatut(TicketStatut.PAYE);
            ticket.setDateAchat(LocalDateTime.now());
            ticket.setQrCode(generateQrCode());
            ticketDao.update(ticket);
            purchasedTickets.add(TicketMapper.toDTO(ticket));
        }

        //  Mettre à jour les places disponibles
        int placesRestantes = evenement.getPlacesDisponibles() - nbreTicket;
        evenement.setPlacesDisponibles(placesRestantes);
        evenementDao.update(evenement);

        //  Retourner les tickets achetés
        return purchasedTickets.getFirst(); // ou get(0)
    }
}
