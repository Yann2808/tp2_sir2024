/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.HashSet;
import java.util.Set;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import rest.EvenementResource;
import rest.UserResource;

@OpenAPIDefinition(
        info = @Info(
                title = "Mon API JAX-RS pour la gestion d'événements",
                version = "1.0.0",
                description = "Documentation de mon API avec OpenAPI"
        )
)

@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
@ApplicationPath("/")
public class RestApp extends Application {


    @Override
    public Set<Class<?>> getClasses() {

        final Set<Class<?>> clazzes = new HashSet<Class<?>>();

        // Ajout de ma ressource pour contacter OpenAPI
        clazzes.add(OpenApiResource.class);

        // Ajout de la ressource CORSFilter pour les permissions de connexion entre API et Frontend
        clazzes.add(CORSFilter.class);
        clazzes.add(PreflightResource.class);

        // Pour l'authentification basique
        clazzes.add(BasicAuthFilter.class);
        clazzes.add(UserResource.class);
        System.out.println("✅ UserResource et BasicAuthFilter bien enregistrées !");

        // Ajout des Ressources
        clazzes.add(EvenementResource.class);
        clazzes.add(ObjectMapperContextResolver.class);
        System.out.println("✅ EvenementResource bien enregistrée !");

        // clazzes.add(AcceptHeaderOpenApiResource.class);


        System.out.println("🚀 RestApp est bien chargée !");
        return clazzes;
    }

}