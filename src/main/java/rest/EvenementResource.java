package rest;

import dto.EvenementDTO;
import entity.Evenement;
import jakarta.ws.rs.*;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import service.EvenementService;

import java.util.List;

@Path("/events")
@Produces("application/json")
@Consumes("application/json")
public class EvenementResource {
    private EvenementService evenementService;

    public EvenementResource () {
        this.evenementService = new EvenementService();
    }

    //  1. Endpoint pour récupérer tous les événements
    @GET
    @Path("/all")
    public List<EvenementDTO> getAllEvenements() {
        return evenementService.getEvenementsByOrganisateur(null); // On récupère tout sans filtre
    }

    //  2. Endpoint pour récupérer les événements d'un organisateur
    @GET
    @Path("/organisateur/{organisateur_id}")
    public List<EvenementDTO> getEvenementsByOrganisateur( @PathParam("organisateur_id") Long organisateur_id) {
        return evenementService.getEvenementsByOrganisateur(organisateur_id);
    }

    // 3. Endpoint pour créer un événement
    @POST
    @Path("/create")
    public Response createEvenement(EvenementDTO evenementDTO) {
        EvenementDTO createdEvenementDto = evenementService.createEvenement(evenementDTO);
        return Response.ok(createdEvenementDto).build();
    }

    // 4. Endpoint pour récupérer un événement par son ID
    @GET
    @Path("/{id}")
    public Response getEvenementById(@PathParam("id") Long id) {
        EvenementDTO evenementDTO = evenementService.getEvenementById(id);
        if (evenementDTO == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(evenementDTO).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEvenement(@PathParam("id") Long id) {
        boolean deletedEvenement = evenementService.deleteEvenement(id);
        if (!deletedEvenement) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        //return Response.ok().build();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
