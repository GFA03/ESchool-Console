package org.example.repositories;

import org.example.models.Parent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParentRepository implements GenericRepository<Parent> {
    private final Connection connection;

    public ParentRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Parent parent) {
        String sql = "INSERT INTO parents (first_name, last_name, date_of_birth, email, phone_number) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, parent.getFirstName());
            statement.setString(2, parent.getLastName());
            statement.setString(3, parent.getDateOfBirth());
            statement.setString(4, parent.getEmail());
            statement.setString(5, parent.getPhoneNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Parent> getAll() {
        List<Parent> parents = new ArrayList<>();
        String sql = "SELECT * FROM parents";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Parent parent = new Parent(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("date_of_birth"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number")
                );
                parents.add(parent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parents;
    }

    @Override
    public Parent get(Long parentId) {
        String sql = "SELECT * FROM parents WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, parentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Parent(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("date_of_birth"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Parent updatedParent) {
        String sql = "UPDATE parents SET first_name = ?, last_name = ?, date_of_birth = ?, email = ?, phone_number = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, updatedParent.getFirstName());
            statement.setString(2, updatedParent.getLastName());
            statement.setString(3, updatedParent.getDateOfBirth());
            statement.setString(4, updatedParent.getEmail());
            statement.setString(5, updatedParent.getPhoneNumber());
            statement.setLong(6, updatedParent.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long parentId) {
        String sql = "DELETE FROM parents WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, parentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getSize() {
        List<Parent> parents = getAll();
        return parents.size();
    }
}
