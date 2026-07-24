package model;

import java.util.*;

public class EtudiantDAO {

    private static List<Etudiant> etudiants = new ArrayList<>();
    private static int compteurId = 4;

    static {
        etudiants.add(new Etudiant(1, "karima",   "Ssss",  "s.karima@email.com", "Informatique",  3));
        etudiants.add(new Etudiant(2, "mohammed", "benh",  "m.benh@email.com",   "Mathématiques", 2));
        etudiants.add(new Etudiant(3, "salma",    "alami", "s.alami@email.com",  "Physique",      1));
    }

    public List<Etudiant> getAllEtudiants() {
        return etudiants;
    }

    public Etudiant getEtudiantById(int id) {
        for (Etudiant e : etudiants) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    public void addEtudiant(Etudiant e) {
        e.setId(compteurId++);
        etudiants.add(e);
    }

    public void updateEtudiant(Etudiant e) {
        for (int i = 0; i < etudiants.size(); i++) {
            if (etudiants.get(i).getId() == e.getId()) {
                etudiants.set(i, e);
                return;
            }
        }
    }

    public void deleteEtudiant(int id) {
        for (Etudiant e : etudiants) {
            if (e.getId() == id) {
                etudiants.remove(e);
                break;
            }
        }
    }
}
