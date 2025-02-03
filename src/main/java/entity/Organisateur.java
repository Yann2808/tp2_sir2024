package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Organisateur extends User{
    private String nomEntreprise;
    private String siret;

    public void setNomEntreprise(String nomEntreprise){
        this.nomEntreprise = nomEntreprise;
    }

    public String getNomEntreprise(){
        return nomEntreprise;
    }

    public void setSiret(String siret){
        this.siret = siret;
    }

    public String getSiret(){
        return siret;
    }

    @OneToMany(mappedBy = "organisateur")
    private List<Evenement> events = new ArrayList<>();
}
