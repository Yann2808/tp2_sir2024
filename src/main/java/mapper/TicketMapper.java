package mapper;

import dto.TicketDTO;
import entity.Ticket;

public class TicketMapper {
    public TicketMapper() {
    }

    public TicketDTO toDTO(Ticket ticket) {
        return new TicketDTO(
                ticket.getId(),
                ticket.getEvenement().getId(),
                ticket.getAcheteur().getId(),
                ticket.getDateAchat().toString(),
                ticket.getPrix(),
                ticket.getNbreTicket(),
                ticket.getStatut(),
                ticket.getQrCode()
        );
    }

    public Ticket toEntity(TicketDTO ticketDTO) {
        return new Ticket(
                ticketDTO.getId(),
                ticketDTO.getEvenementId(),
                ticketDTO.getAcheteurId(),
                ticketDTO.getDateAchat(),
                ticketDTO.getPrix(),
                ticketDTO.getTicketRestant(),
                ticketDTO.getStatut(),
                ticketDTO.getQrCode()
        );
    }
}
