package com.hei.absence.gestion.repository;

import com.hei.absence.gestion.model.Absence;
import com.hei.absence.gestion.DatabaseConnection; // Importation de la classe DatabaseConnection
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AbsenceRepositoryImpl implements AbsenceRepository {

    @Override
    public List<Absence> findAll() {
        List<Absence> absences = new ArrayList<>();
        String sql = "SELECT id, etudiant_id, date_absence, motif, justifiee, cours_id FROM absences";

        try (Connection connection = DatabaseConnection.getConnection(); // Utilisation de DatabaseConnection
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String etudiantId = rs.getString("etudiant_id");
                Date dateAbsence = rs.getDate("date_absence");
                String motif = rs.getString("motif");
                boolean justifiee = rs.getBoolean("justifiee");
                Long coursId = rs.getLong("cours_id");

                LocalDate localDateAbsence = (dateAbsence != null) ? dateAbsence.toLocalDate() : null;

                Absence absence = new Absence(id, etudiantId, localDateAbsence, motif, justifiee, coursId);
                absences.add(absence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return absences;
    }

    @Override
    public boolean existsById(Long id) {
        String sql = "SELECT COUNT(*) FROM absences WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection(); // Utilisation de DatabaseConnection
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void insert(Absence absence) {
        String sql = "INSERT INTO absences (etudiant_id, date_absence, motif, justifiee, cours_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection(); // Utilisation de DatabaseConnection
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, absence.getEtudiantId());
            pstmt.setDate(2, Date.valueOf(absence.getDateAbsence()));
            pstmt.setString(3, absence.getMotif());
            pstmt.setBoolean(4, absence.isJustifiee());
            pstmt.setLong(5, absence.getCoursId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Absence absence) {
        String sql = "UPDATE absences SET etudiant_id = ?, date_absence = ?, motif = ?, justifiee = ?, cours_id = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection(); // Utilisation de DatabaseConnection
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, absence.getEtudiantId());
            pstmt.setDate(2, Date.valueOf(absence.getDateAbsence()));
            pstmt.setString(3, absence.getMotif());
            pstmt.setBoolean(4, absence.isJustifiee());
            pstmt.setLong(5, absence.getCoursId());
            pstmt.setLong(6, absence.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM absences WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection(); // Utilisation de DatabaseConnection
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Absence> findByEtudiantId(String etudiantId) {
        List<Absence> absences = new ArrayList<>();
        String sql = "SELECT * FROM absences WHERE etudiant_id = ?";

        try (Connection connection = DatabaseConnection.getConnection(); // Utilisation de DatabaseConnection
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, etudiantId);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Absence absence = new Absence();
                absence.setId(resultSet.getLong("id"));
                absence.setEtudiantId(resultSet.getString("etudiant_id"));
                absence.setDateAbsence(resultSet.getDate("date_absence") != null ? resultSet.getDate("date_absence").toLocalDate() : null);
                absence.setMotif(resultSet.getString("motif"));
                absence.setJustifiee(resultSet.getBoolean("justifiee"));
                absence.setCoursId(resultSet.getLong("cours_id"));
                absences.add(absence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return absences;
    }

    @Override
    public List<Absence> findByCoursId(Long coursId) {
        List<Absence> absences = new ArrayList<>();
        String sql = "SELECT * FROM absences WHERE cours_id = ?";

        try (Connection connection = DatabaseConnection.getConnection(); // Utilisation de DatabaseConnection
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setLong(1, coursId);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Absence absence = new Absence();
                absence.setId(resultSet.getLong("id"));
                absence.setEtudiantId(resultSet.getString("etudiant_id"));
                absence.setDateAbsence(resultSet.getDate("date_absence") != null ? resultSet.getDate("date_absence").toLocalDate() : null);
                absence.setMotif(resultSet.getString("motif"));
                absence.setJustifiee(resultSet.getBoolean("justifiee"));
                absence.setCoursId(resultSet.getLong("cours_id"));
                absences.add(absence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return absences;
    }

    @Override
    public Absence findById(Long id) {
        Absence absence = null;
        String sql = "SELECT * FROM absences WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection(); // Utilisation de DatabaseConnection
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                absence = new Absence();
                absence.setId(resultSet.getLong("id"));
                absence.setEtudiantId(resultSet.getString("etudiant_id"));
                absence.setDateAbsence(resultSet.getDate("date_absence") != null ? resultSet.getDate("date_absence").toLocalDate() : null);
                absence.setMotif(resultSet.getString("motif"));
                absence.setJustifiee(resultSet.getBoolean("justifiee"));
                absence.setCoursId(resultSet.getLong("cours_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return absence;
    }

    @Override
    public List<Absence> findAllByEtudiant(String etudiantId) {
        return findByEtudiantId(etudiantId); // Réutilisation de la méthode existante
    }
}
