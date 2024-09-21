package com.hei.absence.gestion.repository;

import com.hei.absence.gestion.model.Cours;
import com.hei.absence.gestion.DatabaseConnection; // Importez la classe DatabaseConnection
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CoursRepositoryImpl implements CoursRepository {

    @Override
    public List<Cours> findAll() {
        List<Cours> coursList = new ArrayList<>();
        String query = "SELECT * FROM cours"; // Ajustez la requête selon votre structure de table

        try (Connection connection = DatabaseConnection.getConnection(); // Utilisez la classe DatabaseConnection
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Cours cours = new Cours();
                cours.setId(resultSet.getLong("id")); // Assurez-vous que "id" correspond à votre colonne
                cours.setNom(resultSet.getString("nom")); // Ajustez selon votre colonne
                // Ajoutez d'autres attributs ici selon votre modèle Cours
                coursList.add(cours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coursList;
    }

    @Override
    public void save(Cours cours) {
        String query = "INSERT INTO cours (nom) VALUES (?)"; // Ajustez selon votre table

        try (Connection connection = DatabaseConnection.getConnection(); // Utilisez la classe DatabaseConnection
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, cours.getNom());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cours findById(Long id) {
        Cours cours = null;
        String query = "SELECT * FROM cours WHERE id = ?"; // Ajustez selon votre table

        try (Connection connection = DatabaseConnection.getConnection(); // Utilisez la classe DatabaseConnection
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                cours = new Cours();
                cours.setId(resultSet.getLong("id")); // Assurez-vous que "id" correspond à votre colonne
                cours.setNom(resultSet.getString("nom")); // Ajustez selon votre colonne
                // Ajoutez d'autres attributs ici selon votre modèle Cours
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cours;
    }

    @Override
    public void update(Cours cours) {
        String query = "UPDATE cours SET nom = ? WHERE id = ?"; // Ajustez selon votre table

        try (Connection connection = DatabaseConnection.getConnection(); // Utilisez la classe DatabaseConnection
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, cours.getNom());
            preparedStatement.setLong(2, cours.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM cours WHERE id = ?"; // Ajustez selon votre table

        try (Connection connection = DatabaseConnection.getConnection(); // Utilisez la classe DatabaseConnection
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
