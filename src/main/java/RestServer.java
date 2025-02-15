import io.undertow.Undertow;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

import java.util.logging.Logger;

/**
 * RESTful microservice, based on JAX-RS and JBoss Undertow
 *
 */
public class RestServer {

    private static final Logger logger = Logger.getLogger(RestServer.class.getName());

    public static void main( String[] args ) {

        UndertowJaxrsServer ut = new UndertowJaxrsServer();

        RestApp app = new RestApp();

        ut.deploy(app);

        ut.start(
                Undertow.builder()
                        .addHttpListener(8111, "localhost")

        );

        logger.info("JAX-RS based micro-service running!");
    }
}