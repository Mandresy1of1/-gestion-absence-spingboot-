package com.hei.absence.gestion.repository;

import com.hei.absence.gestion.DatabaseConnection;
import com.hei.absence.gestion.model.Etudiant;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EtudiantRepositoryImpl implements EtudiantRepository {

    @Override
    public List<Etudiant> findAll() {
        List<Etudiant> etudiants = new ArrayList<>();
        String sql = "SELECT * FROM etudiants";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Etudiant etudiant = new Etudiant();
                etudiant.setId(rs.getString("id"));
                etudiant.setNom(rs.getString("nom"));
                etudiant.setPrenom(rs.getString("prenom"));
                etudiant.setEmail(rs.getString("email"));
                etudiants.add(etudiant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error appropriately
        }
        return etudiants;
    }

    @Override
    public void save(Etudiant etudiant) {
        String sql = "INSERT INTO etudiants (id, nom, prenom, email) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, etudiant.getId());
            pstmt.setString(2, etudiant.getNom());
            pstmt.setString(3, etudiant.getPrenom());
            pstmt.setString(4, etudiant.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error appropriately
        }
    }

    @Override
    public Etudiant findById(String id) {
        Etudiant etudiant = null;
        String sql = "SELECT * FROM etudiants WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                etudiant = new Etudiant();
                etudiant.setId(rs.getString("id"));
                etudiant.setNom(rs.getString("nom"));
                etudiant.setPrenom(rs.getString("prenom"));
                etudiant.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error appropriately
        }
        return etudiant;
    }

    @Override
    public void update(Etudiant etudiant) {
        String sql = "UPDATE etudiants SET nom = ?, prenom = ?, email = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, etudiant.getNom());
            pstmt.setString(2, etudiant.getPrenom());
            pstmt.setString(3, etudiant.getEmail());
            pstmt.setString(4, etudiant.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error appropriately
        }
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM etudiants WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle error appropriately
        }
    }
}
