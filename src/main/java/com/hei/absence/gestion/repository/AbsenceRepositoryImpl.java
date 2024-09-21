package com.hei.absence.gestion.repository;

import com.hei.absence.gestion.model.Absence;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AbsenceRepositoryImpl implements AbsenceRepository {
    private final String url = "jdbc:postgresql://localhost:5432/hei_absence_db";
    private final String user = "postgres";
    private final String password = "mann";

    @Override
    public List<Absence> findAll() {
        List<Absence> absences = new ArrayList<>();
        String sql = "SELECT * FROM absences";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Absence absence = new Absence();
                absence.setId(rs.getLong("id"));
                absence.setEtudiantId(rs.getString("etudiant_id"));
                absence.setDateAbsence(rs.getDate("date_absence"));
                absence.setMotif(rs.getString("motif"));
                absence.setJustifiee(rs.getBoolean("justifiee"));
                absences.add(absence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return absences;
    }

    @Override
    public List<Absence> findAllByEtudiant(String etudiantId) {
        List<Absence> absences = new ArrayList<>();
        String sql = "SELECT * FROM absences WHERE etudiant_id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, etudiantId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Absence absence = new Absence();
                absence.setId(rs.getLong("id"));
                absence.setEtudiantId(rs.getString("etudiant_id"));
                absence.setDateAbsence(rs.getDate("date_absence"));
                absence.setMotif(rs.getString("motif"));
                absence.setJustifiee(rs.getBoolean("justifiee"));
                absences.add(absence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return absences;
    }

    @Override
    public void insert(Absence absence) {
        String sql = "INSERT INTO absences (etudiant_id, date_absence, motif, justifiee) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, absence.getEtudiantId());
            pstmt.setDate(2, new java.sql.Date(absence.getDateAbsence().getTime()));
            pstmt.setString(3, absence.getMotif());
            pstmt.setBoolean(4, absence.isJustifiee());
            pstmt.executeUpdate();

            // Récupérer l'ID généré
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                absence.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Absence findById(Long id) {
        Absence absence = null;
        String sql = "SELECT * FROM absences WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                absence = new Absence();
                absence.setId(rs.getLong("id"));
                absence.setEtudiantId(rs.getString("etudiant_id"));
                absence.setDateAbsence(rs.getDate("date_absence"));
                absence.setMotif(rs.getString("motif"));
                absence.setJustifiee(rs.getBoolean("justifiee"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return absence;
    }

    @Override
    public void update(Absence absence) {
        String sql = "UPDATE absences SET etudiant_id = ?, date_absence = ?, motif = ?, justifiee = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, absence.getEtudiantId());
            pstmt.setDate(2, new java.sql.Date(absence.getDateAbsence().getTime()));
            pstmt.setString(3, absence.getMotif());
            pstmt.setBoolean(4, absence.isJustifiee());
            pstmt.setLong(5, absence.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM absences WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
