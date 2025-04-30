package rest;

import dto.TicketDTO;
import dto.TicketPurchaseDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.TicketService;

import java.util.List;

@Path("/tickets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TicketResource {
    private TicketDTO ticketDTO;
    private TicketService ticketService;

    public TicketResource() {
        this.ticketDTO = new TicketDTO();
        this.ticketService = new TicketService();
    }

    // 1. Endpoint pour récupérer tous les tickets
//    @GET
//    @Path("/all")
//    public List<TicketDTO> getAllTickets() {
//        return ticketService.getAllTickets();
//    }

//    @POST
//    @Path("/purchase")
//    public Response purchaseTicket(@QueryParam("evenementId") Long evenementId,
//                                   @QueryParam("acheteurId") Long acheteurId,
//                                   @QueryParam("nbreTicketVoulu") int nbreTicketVoulu)
//    {
//        try {
//            List<TicketDTO> tickets = (List<TicketDTO>) ticketService.purchaseTicket(evenementId, acheteurId, nbreTicketVoulu);
//            return Response.status(Response.Status.CREATED).entity(tickets).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
//        }
//    }

    @POST
    @Path("/purchase")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response purchaseTicket(TicketPurchaseDTO ticketPurchaseDTO) {
        try {
            // Accède aux données envoyées dans le corps de la requête
            Long evenementId = ticketPurchaseDTO.getEvenementId();
            Long acheteurId = ticketPurchaseDTO.getAcheteurId();
            int nbreTicketVoulu = ticketPurchaseDTO.getNbreTicketVoulu();

            // Appel à la logique du service
            List<TicketDTO> tickets = ticketService.purchaseTicket(evenementId, acheteurId, nbreTicketVoulu);

            return Response.status(Response.Status.CREATED).entity(tickets).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }


}
