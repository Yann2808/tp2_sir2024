import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Provider
public class BasicAuthFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BASIC_PREFIX = "Basic ";
    private static final String USERNAME = "admin"; // Nom d'utilisateur attendu
    private static final String PASSWORD = "password"; // Mot de passe attendu

    @Override
    public void filter(ContainerRequestContext requestContext) {
        // Ignorer l'authentification pour Swagger UI et openapi.json
        String path = requestContext.getUriInfo().getPath();
        if (path.startsWith("swagger-ui") || path.equals("openapi.json")) {
            return;
        }

        // Vérifier l'en-tête Authorization
        String authHeader = requestContext.getHeaderString(AUTHORIZATION_HEADER);
        if (authHeader == null || !authHeader.startsWith(BASIC_PREFIX)) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .header("WWW-Authenticate", "Basic realm=\"My API\"")
                            .entity("Missing or invalid Authorization header")
                            .build()
            );
            return;
        }

        // Décoder les identifiants
        String credentials = new String(
                Base64.getDecoder().decode(authHeader.substring(BASIC_PREFIX.length())),
                StandardCharsets.UTF_8
        );
        String[] parts = credentials.split(":");
        if (parts.length != 2) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("Invalid credentials format")
                            .build()
            );
            return;
        }

        String username = parts[0];
        String password = parts[1];

        // Vérifier les identifiants
        if (!USERNAME.equals(username) || !PASSWORD.equals(password)) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("Invalid username or password")
                            .build()
            );
        }
    }
}