package model;

import java.io.Serializable;

public class Etudiant implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String nom; 
    private String prenom;
    private String email;
    private String filiere;
    private int niveau;

    
    public Etudiant() {}

    public Etudiant(int id, String nom, String prenom, String email, String filiere, int niveau) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.filiere = filiere;
        this.niveau = niveau;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFiliere() { return filiere; }
    public void setFiliere(String filiere) { this.filiere = filiere; }

    public int getNiveau() { return niveau; }
    public void setNiveau(int niveau) { this.niveau = niveau; }
}