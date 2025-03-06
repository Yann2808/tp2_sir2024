package rest;

import dto.EvenementDTO;
import entity.Evenement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    public EvenementResource () {
        this.evenementService = new EvenementService();
    }

    //  1. Endpoint pour récupérer tous les événements
    @GET
    @Operation(summary = "Get all events", description = "Get all events without filters")
    @ApiResponse(responseCode = "200", description = "Success")
    @Path("/all")
    public List<EvenementDTO> getAllEvenements() {
        return evenementService.getAllEvenements(); // On récupère tout sans filtre
    }

    //  2. Endpoint pour récupérer les événements d'un organisateur
    @GET
    @Path("/organisateur/{organisateur_id}")
    public List<EvenementDTO> getEvenementsByOrganisateur( @PathParam("organisateur_id") Long organisateur_id) {
        return evenementService.getEvenementsByOrganisateur(organisateur_id);
    }

    // 3. Endpoint pour créer un événement
//    @POST
//    @Path("/create")
//    @Consumes("application/json")
//    @Produces("application/json")
//    public Response createEvenement(EvenementDTO evenementDTO) {
//        EvenementDTO createdEvenementDto = evenementService.createEvenement(evenementDTO);
//        return Response.ok(createdEvenementDto).build();
//    }

    @GET
    @Path("/")
    @Produces("text/plain")
    public String rootEndpoint() {
        System.out.println("✅ Endpoint / (racine) atteint !");
        return "Bienvenue sur le serveur REST API !";
    }

    @POST
    @Path("/create")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createEvenement(EvenementDTO evenementDTO) {
        System.out.println("✅ Requête POST reçue : " + evenementDTO); // Vérifiez les logs
        EvenementDTO createdEvenementDto = evenementService.createEvenement(evenementDTO);
        System.out.println("✅ Événement créé : " + createdEvenementDto);
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

//    @Path("/events")
//    public class EvenementResource {
//
//        @GET
//        @Path("/") // Endpoint root pour `/api/events/`
//        @Produces("text/plain")
//        public String root() {
//            System.out.println("✅ Endpoint root (/api/events) atteint !");
//            return "Endpoint `/api/events/` fonctionne !";
//        }
//
//        @GET
//        @Path("/ping") // Endpoint pour tester `/api/events/ping`
//        @Produces("text/plain")
//        public String ping() {
//            System.out.println("✅ Endpoint /ping atteint !");
//            return "Ping reçu avec succès !";
//        }
//    }

