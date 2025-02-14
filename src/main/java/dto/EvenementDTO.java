package dto;

import java.time.LocalDateTime;

public class EvenementDTO {
    private Long id;
    private String nom;
    private String description;
    private LocalDateTime date;
    private String lieu;
    private Double prix;
    private Integer placesDisponibles;
    private Long organisateurId; // On stocke juste l'ID de l'organisateur

    public EvenementDTO() {}

    public EvenementDTO(Long id, String nom, String description, LocalDateTime date, String lieu, Double prix, Integer placesDisponibles, Long organisateurId) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date = date;
        this.lieu = lieu;
        this.prix = prix;
        this.placesDisponibles = placesDisponibles;
        this.organisateurId = organisateurId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public String getLieu() { return lieu; }
    public void setLieu(String lieu) { this.lieu = lieu; }

    public Double getPrix() { return prix; }
    public void setPrix(Double prix) { this.prix = prix; }

    public Integer getPlacesDisponibles() { return placesDisponibles; }
    public void setPlacesDisponibles(Integer placesDisponibles) { this.placesDisponibles = placesDisponibles; }

    public Long getOrganisateurId() { return organisateurId; }
    public void setOrganisateurId(Long organisateurId) { this.organisateurId = organisateurId; }
}
