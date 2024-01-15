package com.example.tasklist.repository.impl;

import com.example.tasklist.domain.exception.ResourceMappingException;
import com.example.tasklist.domain.team.Team;
import com.example.tasklist.repository.DataSourceConfig;
import com.example.tasklist.repository.TeamRepository;
import com.example.tasklist.repository.mappers.TeamRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class TeamRepositoryImpl implements TeamRepository {

    private final DataSourceConfig dataSourceConfig;

    private final String FIND_BY_ID = """
            SELECT t.id          as team_id,
                   t.name       as team_name,
                   t.logo        as team_logo,
                   t.created_at  as team_created_at,
                   t.updated_at  as team_updated_at
            FROM ingame365.teams t
            WHERE id = ?""";

    private final String FIND_ALL_BY_USER_ID = """
            SELECT t.id          as team_id,
                   t.name        as team_name,
                   t.logo        as team_logo,
                   t.created_at  as team_created_at,
                   t.updated_at  as team_updated_at
            FROM ingame365.teams t
            JOIN ingame365.users_teams ut on t.id = ut.team_id
            WHERE ut.user_id = ?""";

    private final String ASSIGN_TO_USER_BY_ID = """
            INSERT INTO ingame365.users_teams (user_id, team_id)
            VALUES (?, ?)""";

    private final String UPDATE = """
            UPDATE ingame365.teams
            SET name = ?,
                logo = ?,
                updated_at = ?
            WHERE id = ?""";

    private final String CREATE = """
            INSERT INTO ingame365.teams (name, logo, created_at, updated_at)
            VALUES (?, ?, ?, ?)""";

    private final String DELETE = """
            DELETE FROM ingame365.teams
            WHERE id = ?""";


    @Override
    public Optional<Team> findById(Long id) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(FIND_BY_ID);
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                return Optional.ofNullable(TeamRowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new ResourceMappingException("Error while finding team by id");
        }
    }

    @Override
    public List<Team> findAllByUserId(Long userId) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(FIND_ALL_BY_USER_ID);
            statement.setLong(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                return TeamRowMapper.mapRows(rs);
            }
        } catch (SQLException e) {
            throw new ResourceMappingException("Error while finding all teams by user id");
        }
    }

    @Override
    public void assignToUserById(Long teamId, Long userId) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(ASSIGN_TO_USER_BY_ID);
            statement.setLong(1, teamId);
            statement.setLong(2, userId);


            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            throw new ResourceMappingException("Error while assigning team to user");
        }
    }

    @Override
    public void update(Team team) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(UPDATE);
            statement.setString(1, team.getName());
            if (team.getLogo() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, team.getLogo());
            }
            statement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            statement.setLong(4, team.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ResourceMappingException("Error while updating team");
        }
    }

    @Override
    public void create(Team team) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(CREATE,
                            PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setString(1, team.getName());
            if (team.getLogo() == null) {
                statement.setNull(2, Types.VARCHAR);
            } else {
                statement.setString(2, team.getLogo());
            }
            statement.setTimestamp(3,
                    new Timestamp(System.currentTimeMillis()));
            statement.setNull(4, Types.TIMESTAMP);
            statement.executeUpdate();
            try (ResultSet rs = statement.getGeneratedKeys()) {
                rs.next();
                team.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new ResourceMappingException("Error while creating team");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = dataSourceConfig.getConnection();
            PreparedStatement statement =
                    connection.prepareStatement(DELETE);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ResourceMappingException("Error while deleting team");
        }
    }
}
