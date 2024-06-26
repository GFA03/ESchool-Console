package org.example.repositories;

import org.example.models.Group;
import org.example.services.AuditService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupRepository implements GenericRepository<Group> {
    private final Connection connection;
    private final AuditService auditService;

    public GroupRepository(Connection connection, AuditService auditService) {
        this.connection = connection;
        this.auditService = auditService;
    }

    @Override
    public void add(Group group) {
        String query = "INSERT INTO `groups` (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, group.getName());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Failed to add group, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    group.setId(generatedKeys.getLong(1));
                    auditService.logAdd("Group", group.getId().toString());
                } else {
                    throw new SQLException("Failed to add group, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Group> getAll() {
        List<Group> groups = new ArrayList<>();
        String query = "SELECT * FROM `groups`";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Group group = new Group(resultSet.getLong("id"), resultSet.getString("name"));
                groups.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        auditService.logGet("All Groups", null);
        return groups;
    }

    @Override
    public Group get(Long groupId) {
        String query = "SELECT * FROM `groups` WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, groupId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    auditService.logGet("Group", groupId.toString());
                    return new Group(resultSet.getLong("id"), resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Group updatedGroup) {
        String query = "UPDATE `groups` SET name = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, updatedGroup.getName());
            statement.setLong(2, updatedGroup.getId());
            statement.executeUpdate();
            auditService.logUpdate("Group", updatedGroup.getId().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long groupId) {
        String query = "DELETE FROM `groups` WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, groupId);
            statement.executeUpdate();
            auditService.logDelete("Group", groupId.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getSize() {
        String query = "SELECT COUNT(*) AS count FROM `groups`";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
