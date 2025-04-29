import jakarta.ws.rs.OPTIONS;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/")
public class PreflightResource {

    @OPTIONS
    @Path("{path: .*}")
    public Response handlePreflight() {
        return Response.ok().build();
    }
}
