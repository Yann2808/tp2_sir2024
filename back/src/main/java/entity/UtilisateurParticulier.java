package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UtilisateurParticulier extends User{
    private String nom;
    private String prenom;
    private String adresse;
    private String codePostal;
    private String ville;
    private String telephone;
    private LocalDate dateNaissance;

    @OneToMany(mappedBy = "acheteur")
    private List<Ticket> tickets = new ArrayList<>();

    public void setNom(String nom){
        this.nom = nom;
    }

    public String getNom(){
        return nom;
    }

    public void setPrenom(String prenom){
        this.prenom = prenom;
    }

    public String getPrenom(){
        return prenom;
    }

    public void setAdresse(String adresse){
        this.adresse = adresse;
    }

    public String getAdresse(){
        return adresse;
    }

    public void setCodePostal(String codePostal){
        this.codePostal = codePostal;
    }

    public String getCodePostal(){
        return codePostal;
    }

    public void setVille(String ville){
        this.ville = ville;
    }

    public String getVille(){
        return ville;
    }

    public void setTelephone(String telephone){
        this.telephone = telephone;
    }

    public String getTelephone(){
        return telephone;
    }

    public void setDateNaissance(LocalDate dateNaissance){
        this.dateNaissance = dateNaissance;
    }

    public LocalDate getDateNaissance(){
        return dateNaissance;
    }

}
