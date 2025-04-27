import io.undertow.Undertow;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.server.handlers.resource.ResourceHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import java.util.logging.Logger;

/**
 * RESTful microservice, based on JAX-RS and JBoss Undertow
 */
public class RestServer {

    private static final Logger logger = Logger.getLogger(RestServer.class.getName());

    public static void main(String[] args) {
        UndertowJaxrsServer server = new UndertowJaxrsServer();

        RestApp restApp = new RestApp();

        // Configurer le déploiement avec JAX-RS et le servlet Swagger UI
        DeploymentInfo deploymentInfo = server.undertowDeployment(RestApp.class)
                .setClassLoader(RestServer.class.getClassLoader())
                .setContextPath("/")
                .setDeploymentName("my-app.war")
                .addServlets(
                        Servlets.servlet("SwaggerUIServlet", SwaggerUIServlet.class)
                                .addMapping("/swagger-ui/*")
                );

        // Déployer l'application
        server.deploy(deploymentInfo);


        // Démarrer le serveur
        server.start(
                Undertow.builder()
                        .addHttpListener(8111, "localhost")
        );

        logger.info("JAX-RS based micro-service running with Swagger UI!");
    }
}