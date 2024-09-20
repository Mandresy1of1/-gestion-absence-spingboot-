package com.hei.absence.gestion.repository;

import com.hei.absence.gestion.model.Absence;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class AbsenceRepository {
    private final Connection connection;

    public AbsenceRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Absence> findAll() {
        List<Absence> absences = new ArrayList<>();
        String query = "SELECT * FROM absences";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Absence absence = new Absence(
                        rs.getLong("id"),
                        rs.getLong("etudiant_id"),
                        rs.getLong("cours_id"),
                        rs.getDate("date_absence").toLocalDate(), // Conversion
                        rs.getBoolean("justifiee")
                );
                absences.add(absence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return absences;
    }

    public void insert(Absence absence) {
        String query = "INSERT INTO absences (etudiant_id, cours_id, date_absence, justifiee) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, absence.getEtudiantId());
            stmt.setLong(2, absence.getCoursId());
            stmt.setDate(3, java.sql.Date.valueOf(absence.getDateAbsence())); // Conversion
            stmt.setBoolean(4, absence.isEstJustifiee());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Absence absence) {
        String query = "UPDATE absences SET etudiant_id = ?, cours_id = ?, date_absence = ?, justifiee = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, absence.getEtudiantId());
            stmt.setLong(2, absence.getCoursId());
            stmt.setDate(3, java.sql.Date.valueOf(absence.getDateAbsence())); // Conversion
            stmt.setBoolean(4, absence.isEstJustifiee());
            stmt.setLong(5, absence.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        String query = "DELETE FROM absences WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
