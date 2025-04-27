import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;


public class SwaggerUIServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(SwaggerUIServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo() == null ? "/" : req.getPathInfo();
        logger.info("Request path: " + path);

        if (path.equals("/")) {
            logger.info("Redirecting to Swagger UI");
            resp.sendRedirect("/swagger-ui/index.html?url=http://localhost:8111/openapi.json");
        } else {
            // Chemin correct dans le JAR
            String resourcePath = "/META-INF/resources/webjars/swagger-ui/5.20.7" + path;
            logger.info("Attempting to serve: " + resourcePath);

            // Tester le ClassLoader
            ClassLoader classLoader = this.getClass().getClassLoader();
            logger.info("Using ClassLoader: " + classLoader.getClass().getName());

            // Charger la ressource
            InputStream resourceStream = classLoader.getResourceAsStream(resourcePath);
            if (resourceStream == null) {
                // Essayer sans le préfixe initial '/'
                resourcePath = resourcePath.startsWith("/") ? resourcePath.substring(1) : resourcePath;
                logger.info("Retrying with path: " + resourcePath);
                resourceStream = classLoader.getResourceAsStream(resourcePath);
            }

            if (resourceStream == null) {
                logger.severe("Resource not found: " + resourcePath);
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not found: " + resourcePath);
                return;
            }

            logger.info("Resource found: " + resourcePath);

            // Définir le type de contenu
            resp.setStatus(HttpServletResponse.SC_OK);
            if (path.endsWith(".html")) {
                resp.setContentType("text/html");
            } else if (path.endsWith(".js")) {
                resp.setContentType("application/javascript");
            } else if (path.endsWith(".css")) {
                resp.setContentType("text/css");
            } else if (path.endsWith(".png")) {
                resp.setContentType("image/png");
            }

            // Envoyer la ressource
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = resourceStream.read(buffer)) != -1) {
                resp.getOutputStream().write(buffer, 0, bytesRead);
            }
            resourceStream.close();
        }
    }
}