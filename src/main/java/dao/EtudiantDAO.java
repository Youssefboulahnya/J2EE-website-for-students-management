package dao;

import model.Etudiant;
import java.sql.*;
import java.util.*;

public class EtudiantDAO {

   
    public List<Etudiant> getAllEtudiants() {
        List<Etudiant> liste = new ArrayList<>();
        String sql = "SELECT * FROM etudiant ORDER BY Id DESC";

        try (Connection conn = DBCConnection.getConnection();
             Statement  st   = conn.createStatement();
             ResultSet  rs   = st.executeQuery(sql)) {

            while (rs.next()) {
                liste.add(new Etudiant(
                        rs.getInt("Id"),
                        rs.getString("Nom"),
                        rs.getString("Prenom"),
                        rs.getString("Email"),
                        rs.getString("Filiere"),
                        rs.getInt("Niveau")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    
    public Etudiant getEtudiantById(int id) {
        String sql = "SELECT * FROM etudiant WHERE Id = ?";

        try (Connection conn = DBCConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Etudiant(
                            rs.getInt("Id"),
                            rs.getString("Nom"),
                            rs.getString("Prenom"),
                            rs.getString("Email"),
                            rs.getString("Filiere"),
                            rs.getInt("Niveau")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public void addEtudiant(Etudiant e) {
        String sql = "INSERT INTO etudiant (Nom, Prenom, Email, Filiere, Niveau) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBCConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, e.getNom());
            ps.setString(2, e.getPrenom());
            ps.setString(3, e.getEmail());
            ps.setString(4, e.getFiliere());
            ps.setInt(5, e.getNiveau());
            ps.executeUpdate();

            
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    e.setId(keys.getInt(1));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

   
    public void updateEtudiant(Etudiant e) {
        String sql = "UPDATE etudiant SET Nom=?, Prenom=?, Email=?, Filiere=?, Niveau=? WHERE Id=?";

        try (Connection conn = DBCConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getNom());
            ps.setString(2, e.getPrenom());
            ps.setString(3, e.getEmail());
            ps.setString(4, e.getFiliere());
            ps.setInt(5, e.getNiveau());
            ps.setInt(6, e.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
    public void deleteEtudiant(int id) {
        String sql = "DELETE FROM etudiant WHERE Id = ?";

        try (Connection conn = DBCConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
