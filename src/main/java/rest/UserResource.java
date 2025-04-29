package rest;

import entity.Organisateur;
import entity.User;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import service.UserService;
import entity.UtilisateurParticulier;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserService userService;

    public UserResource() {
        // Constructeur par défaut requis par RESTEasy
        this.userService = new UserService();
    }

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Path("/register")
    public Response register(RegisterRequest request) {
        // Vérifier si le nom d'utilisateur existe déjà
        if (UserService.findUserByUsername(request.getUsername()) != null) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Nom d'utilisateur déjà pris")
                    .build();
        }

        // Créer l'utilisateur selon le type
        User user;
        if ("ORGANISATEUR".equals(request.getType())) {
            user = new Organisateur(
                    request.getUsername(),
                    request.getPassword(),
                    request.getEmail(),
                    request.getNomEntreprise(),
                    request.getSiret()
            );
        } else if ("UTILISATEUR_PARTICULIER".equals(request.getType())) {
            user = new UtilisateurParticulier(
                    request.getUsername(),
                    request.getPassword(),
                    request.getEmail(),
                    request.getNom(),
                    request.getPrenom(),
                    request.getAdresse(),
                    request.getCodePostal(),
                    request.getVille(),
                    request.getTelephone(),
                    LocalDate.parse(request.getDateNaissance())
            );
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Type d'utilisateur invalide")
                    .build();
        }

        // Inscrire l'utilisateur
        UserService.registerUser(user);
        return Response.status(Response.Status.CREATED).entity("Utilisateur créé avec succès").build();
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Context HttpHeaders headers) {
        List<String> authHeader = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || authHeader.isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Authorization header missing").build();
        }

        String encodedCredentials = authHeader.get(0).replace("Basic ", "");
        String decoded = new String(Base64.getDecoder().decode(encodedCredentials));
        String[] parts = decoded.split(":");

        if (parts.length != 2) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Authorization format").build();
        }

        String username = parts[0];
        String password = parts[1];

        User user = UserService.authenticate(username, password);
        if (user == null) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials").build();
        }

        // Tu peux aussi créer un DTO ici pour ne pas renvoyer le mot de passe hashé
        user.setPassword(null); // sécurité : ne pas exposer le mot de passe
        return Response.ok(user).build();
    }


    // Classe DTO pour la requête d'inscription
    public static class RegisterRequest {
        private String username;
        private String password;
        private String email;
        private String type; // "ORGANISATEUR" ou "UTILISATEUR_PARTICULIER"
        private String nomEntreprise; // Pour Organisateur
        private String siret; // Pour Organisateur
        private String nom; // Pour UtilisateurParticulier
        private String prenom; // Pour UtilisateurParticulier
        private String adresse; // Pour UtilisateurParticulier
        private String codePostal; // Pour UtilisateurParticulier
        private String ville; // Pour UtilisateurParticulier
        private String telephone; // Pour UtilisateurParticulier
        private String dateNaissance; // Pour UtilisateurParticulier (format "YYYY-MM-DD")

        // Getters et Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getNomEntreprise() {
            return nomEntreprise;
        }

        public void setNomEntreprise(String nomEntreprise) {
            this.nomEntreprise = nomEntreprise;
        }

        public String getSiret() {
            return siret;
        }

        public void setSiret(String siret) {
            this.siret = siret;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        public String getAdresse() {
            return adresse;
        }

        public void setAdresse(String adresse) {
            this.adresse = adresse;
        }

        public String getCodePostal() {
            return codePostal;
        }

        public void setCodePostal(String codePostal) {
            this.codePostal = codePostal;
        }

        public String getVille() {
            return ville;
        }

        public void setVille(String ville) {
            this.ville = ville;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getDateNaissance() {
            return dateNaissance;
        }

        public void setDateNaissance(String dateNaissance) {
            this.dateNaissance = dateNaissance;
        }
    }
}