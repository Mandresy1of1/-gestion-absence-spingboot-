package com.hei.absence.gestion.repository;

import com.hei.absence.gestion.model.Absence;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AbsenceRepositoryImpl implements AbsenceRepository {
    private final String url = "jdbc:postgresql://localhost:5432/hei_absence_db";
    private final String user = "postgres";
    private final String password = "mann";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public List<Absence> findAll() {
        List<Absence> absences = new ArrayList<>();
        String sql = "SELECT * FROM Absences";
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Absence absence = mapRowToAbsence(rs);
                absences.add(absence);
            }
        } catch (SQLException e) {
            // Ajout d'un log pour suivre les erreurs
            System.err.println("Erreur lors de la récupération des absences : " + e.getMessage());
        }
        return absences;
    }

    @Override
    public void insert(Absence absence) {
        String sql = "INSERT INTO Absences (etudiant_id, date_absence, motif, justifiee, cours_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, absence.getEtudiantId());
            pstmt.setDate(2, absence.getDateAbsence() != null ? Date.valueOf(absence.getDateAbsence()) : null);
            pstmt.setString(3, absence.getMotif());
            pstmt.setBoolean(4, absence.isJustifiee());
            pstmt.setLong(5, absence.getCoursId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'insertion de l'absence : " + e.getMessage());
        }
    }

    @Override
    public void update(Absence absence) {
        String sql = "UPDATE Absences SET etudiant_id = ?, date_absence = ?, motif = ?, justifiee = ?, cours_id = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, absence.getEtudiantId());
            pstmt.setDate(2, absence.getDateAbsence() != null ? Date.valueOf(absence.getDateAbsence()) : null);
            pstmt.setString(3, absence.getMotif());
            pstmt.setBoolean(4, absence.isJustifiee());
            pstmt.setLong(5, absence.getCoursId());
            pstmt.setLong(6, absence.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise à jour de l'absence : " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM Absences WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'absence : " + e.getMessage());
        }
    }

    @Override
    public List<Absence> findByCoursId(Long coursId) {
        List<Absence> absences = new ArrayList<>();
        String sql = "SELECT * FROM Absences WHERE cours_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, coursId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Absence absence = mapRowToAbsence(rs);
                    absences.add(absence);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des absences par cours : " + e.getMessage());
        }
        return absences;
    }

    @Override
    public Absence findById(Long id) {
        String sql = "SELECT * FROM Absences WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToAbsence(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de l'absence par ID : " + e.getMessage());
        }
        return null; // Retourne null si non trouvé
    }

    @Override
    public List<Absence> findAllByEtudiant(String etudiantId) {
        List<Absence> absences = new ArrayList<>();
        String sql = "SELECT * FROM Absences WHERE etudiant_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, etudiantId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Absence absence = mapRowToAbsence(rs);
                    absences.add(absence);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des absences par étudiant : " + e.getMessage());
        }
        return absences;
    }

    private Absence mapRowToAbsence(ResultSet rs) throws SQLException {
        return new Absence(
                rs.getLong("id"),
                rs.getString("etudiant_id"),
                rs.getObject("date_absence", LocalDate.class), // Utilisation de getObject pour éviter les nulls
                rs.getString("motif"),
                rs.getBoolean("justifiee"),
                rs.getLong("cours_id")
        );
    }
}
