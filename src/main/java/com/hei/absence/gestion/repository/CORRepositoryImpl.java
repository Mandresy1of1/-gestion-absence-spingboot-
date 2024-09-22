package com.hei.absence.gestion.repository;

import com.hei.absence.gestion.model.COR;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CORRepositoryImpl implements CORRepository {
    private final String url = "jdbc:postgresql://localhost:5432/hei_absence_db";
    private final String user = "postgres";
    private final String password = "mann";

    // Méthode pour obtenir une connexion à la base de données
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public void insert(COR cor) {
        String sql = "INSERT INTO cor (etudiant_id, date_convocation, statut) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, cor.getEtudiantId());
            pstmt.setDate(2, Date.valueOf(cor.getDateConvocation()));
            pstmt.setString(3, cor.getStatut());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Vous pouvez lancer une exception personnalisée ou gérer l'erreur différemment
        }
    }

    @Override
    public List<COR> findAll() {
        List<COR> cors = new ArrayList<>();
        String sql = "SELECT id, etudiant_id, date_convocation, statut FROM cor";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                COR cor = new COR();
                cor.setId(rs.getLong("id"));
                cor.setEtudiantId(rs.getString("etudiant_id"));
                cor.setDateConvocation(rs.getDate("date_convocation").toLocalDate());
                cor.setStatut(rs.getString("statut"));
                cors.add(cor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Vous pouvez lancer une exception personnalisée ou gérer l'erreur différemment
        }
        return cors;
    }

    @Override
    public COR findById(Long id) {
        COR cor = null;
        String sql = "SELECT id, etudiant_id, date_convocation, statut FROM cor WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cor = new COR();
                    cor.setId(rs.getLong("id"));
                    cor.setEtudiantId(rs.getString("etudiant_id"));
                    cor.setDateConvocation(rs.getDate("date_convocation").toLocalDate());
                    cor.setStatut(rs.getString("statut"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Vous pouvez lancer une exception personnalisée ou gérer l'erreur différemment
        }
        return cor;
    }

    @Override
    public void update(COR cor) {
        String sql = "UPDATE cor SET etudiant_id = ?, date_convocation = ?, statut = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, cor.getEtudiantId());
            pstmt.setDate(2, Date.valueOf(cor.getDateConvocation()));
            pstmt.setString(3, cor.getStatut());
            pstmt.setLong(4, cor.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Vous pouvez lancer une exception personnalisée ou gérer l'erreur différemment
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM cor WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Vous pouvez lancer une exception personnalisée ou gérer l'erreur différemment
        }
    }
}
