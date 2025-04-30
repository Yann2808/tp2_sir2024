package mapper;

import dto.TicketDTO;
import entity.Ticket;

public class TicketMapper {

    public TicketMapper() {
    }

    public static TicketDTO toDTO(Ticket ticket) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setId(ticket.getId());
        ticketDTO.setDateAchat(ticket.getDateAchat()); // <-- maintenant LocalDateTime
        ticketDTO.setDateCreation(ticket.getDateCreation()); // <-- à ne pas oublier
        ticketDTO.setPrix(ticket.getPrix());
        ticketDTO.setTicketRestant(ticket.getNbreTicket());
        ticketDTO.setStatut(ticket.getStatut());
        ticketDTO.setQrCode(ticket.getQrCode());

        if (ticket.getEvenement() != null) {
            ticketDTO.setEvenementId(ticket.getEvenement().getId());
        }
        if (ticket.getAcheteur() != null) {
            ticketDTO.setAcheteurId(ticket.getAcheteur().getId());
        }

        return ticketDTO;
    }

    public static Ticket toEntity(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDTO.getId());
        ticket.setDateAchat(ticketDTO.getDateAchat()); // <-- LocalDateTime
        ticket.setDateCreation(ticketDTO.getDateCreation()); // <-- idem
        ticket.setPrix(ticketDTO.getPrix());
        ticket.setNbreTicket(ticketDTO.getTicketRestant());
        ticket.setStatut(ticketDTO.getStatut());
        ticket.setQrCode(ticketDTO.getQrCode());

        // Les relations (evenement, acheteur) seront gérées dans le service
        return ticket;
    }
}
