package rest;

import dto.EvenementDTO;
import entity.Evenement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
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
    private final EvenementService evenementService;

    public EvenementResource() {
        this.evenementService = new EvenementService();
    }

    // 1. Endpoint pour créer un événement
    @POST
    @Operation(summary = "Create an event", description = "Create an event with the given data")
    @ApiResponse(responseCode = "200", description = "Success")
    @Path("/create")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createEvenement(EvenementDTO evenementDTO) {
        System.out.println("✅ Requête POST reçue : " + evenementDTO); // Vérifiez les logs
        EvenementDTO createdEvenementDto = evenementService.createEvenement(evenementDTO);
        System.out.println("✅ Événement créé : " + createdEvenementDto);
        return Response.ok(createdEvenementDto).build();
    }

    //  2. Endpoint pour récupérer tous les événements
    @GET
    @Operation(summary = "Get all events", description = "Get all events without filters")
    @ApiResponse(responseCode = "200", description = "Success")
    @Path("/all")
    public List<EvenementDTO> getAllEvenements() {
        return evenementService.getAllEvenements(); // On récupère tout sans filtre
    }

    // 3. Endpoint pour récupérer un événement par son ID
    @GET
    @Path("/{id}")
    public Response getEvenementById(@PathParam("id") Long id) {
        EvenementDTO evenementDTO = evenementService.getEvenementById(id);
        if (evenementDTO == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(evenementDTO).build();
    }

    //  4. Endpoint pour récupérer les événements d'un organisateur
    @GET
    @Path("/organisateur/{organisateur_id}")
    public List<EvenementDTO> getEvenementsByOrganisateur(@PathParam("organisateur_id") Long organisateur_id) {
        return evenementService.getEvenementsByOrganisateur(organisateur_id);
    }

    //  5. Endpoint pour modifier un événement
    @PUT
    @Path("/{id}")
    public Response updateEvenement(@PathParam("id") Long id, @Valid EvenementDTO evenementDTO) {
        if (evenementDTO.getId() != null && !evenementDTO.getId().equals(id)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("L'ID dans le chemin doit correspondre à l'ID dans le corps de la requête.")
                    .build();
        }
        evenementDTO.setId(id);

        try {
            EvenementDTO updatedEvenement = evenementService.updateEvenement(evenementDTO);
            return Response.ok(updatedEvenement).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    //  6. Endpoint pour supprimer un événement
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

