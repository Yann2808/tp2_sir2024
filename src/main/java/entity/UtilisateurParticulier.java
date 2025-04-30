package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("UTILISATEUR_PARTICULIER")
public class UtilisateurParticulier extends User {
    private String nom;
    private String prenom;
    private String adresse;
    private String codePostal;
    private String ville;
    private String telephone;
    private LocalDate dateNaissance;

    @OneToMany(mappedBy = "acheteur")
    @JsonIgnore
    private List<Ticket> tickets = new ArrayList<>();

    // Constructeurs
    public UtilisateurParticulier() {}

    public UtilisateurParticulier(String username, String password, String email, String nom, String prenom, String adresse, String codePostal, String ville, String telephone, LocalDate dateNaissance) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setRole("UTILISATEUR_PARTICULIER");
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.telephone = telephone;
        this.dateNaissance = dateNaissance;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getVille() {
        return ville;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}