import entity.User;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.UserService;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Provider
public class BasicAuthFilter implements ContainerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicAuthFilter.class);
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BASIC_PREFIX = "Basic ";

    @Override
    public void filter(ContainerRequestContext requestContext) {
        // Ignorer l'authentification pour Swagger UI, openapi.json, et l'inscription
        String path = requestContext.getUriInfo().getPath();
        LOGGER.info("Processing request for path: {}", path);

        // Normaliser le chemin pour gérer les barres obliques initiales et finales
        String normalizedPath = path.startsWith("/") ? path.substring(1) : path;
        normalizedPath = normalizedPath.endsWith("/") ? normalizedPath.substring(0, normalizedPath.length() - 1) : normalizedPath;

        if (normalizedPath.startsWith("swagger-ui") ||
                normalizedPath.equals("openapi.json") ||
                normalizedPath.equals("users/register")) {
            LOGGER.info("Skipping authentication for path: {}", normalizedPath);
            return;
        }

        // Vérifier l'en-tête Authorization
        String authHeader = requestContext.getHeaderString(AUTHORIZATION_HEADER);
        if (authHeader == null || !authHeader.startsWith(BASIC_PREFIX)) {
            LOGGER.warn("Missing or invalid Authorization header for path: {}", normalizedPath);
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .header("WWW-Authenticate", "Basic realm=\"My API\"")
                            .entity("Missing or invalid Authorization header")
                            .build()
            );
            return;
        }

        // Décoder les identifiants
        LOGGER.info("Decoding Authorization header: {}", authHeader);
        String credentials = new String(
                Base64.getDecoder().decode(authHeader.substring(BASIC_PREFIX.length())),
                StandardCharsets.UTF_8
        );
        String[] parts = credentials.split(":");
        if (parts.length != 2) {
            LOGGER.warn("Invalid credentials format for path: {}", normalizedPath);
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("Invalid credentials format")
                            .build()
            );
            return;
        }

        String username = parts[0];
        String password = parts[1];
        LOGGER.info("Attempting to authenticate user: {}", username);

        // Authentifier l'utilisateur
        User user = UserService.authenticate(username, password);
        if (user == null) {
            LOGGER.warn("Authentication failed for user: {}", username);
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("Invalid username or password")
                            .build()
            );
            return;
        }

        LOGGER.info("Authentication successful for user: {}", username);
        // Stocker l'utilisateur dans le contexte pour l'utiliser dans les endpoints
        requestContext.setProperty("authenticatedUser", user);
    }
}