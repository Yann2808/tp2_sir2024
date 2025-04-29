package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("ORGANISATEUR")
public class Organisateur extends User {
    private String nomEntreprise;
    private String siret;

    @OneToMany(mappedBy = "organisateur", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Evenement> events = new ArrayList<>();

    // Constructeurs
    public Organisateur() {}

    public Organisateur(String username, String password, String email, String nomEntreprise, String siret) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setRole("ORGANISATEUR");
        this.nomEntreprise = nomEntreprise;
        this.siret = siret;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getSiret() {
        return siret;
    }

    public List<Evenement> getEvents() {
        return events;
    }

    public void setEvents(List<Evenement> events) {
        this.events = events;
    }
}
