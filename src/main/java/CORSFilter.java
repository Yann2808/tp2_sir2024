import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CORSFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) {
        MultivaluedMap<String, Object> headers = containerResponseContext.getHeaders();

        headers.add("Access-Control-Allow-Origin", "*"); // Permet l'accès depuis n'importe quel domaine
        headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT"); // Méthodes HTTP autorisées
        headers.add("Access-Control-Allow-Headers", "Content-Type, Authorization"); // Headers autorisés
    }
}
