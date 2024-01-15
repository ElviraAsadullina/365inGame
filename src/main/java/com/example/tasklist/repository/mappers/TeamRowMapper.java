package com.example.tasklist.repository.mappers;

import com.example.tasklist.domain.team.Team;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TeamRowMapper {

    @SneakyThrows
    public static Team mapRow(ResultSet resultSet) {
        if (resultSet.next()) {
            Team team = new Team();
            team.setId(resultSet.getLong("team_id"));
            team.setName(resultSet.getString("team_name"));
            team.setLogo(resultSet.getString("team_logo"));
            team.setCreatedAt(resultSet.getTimestamp("team_created_at").toLocalDateTime());
            Timestamp timestamp = resultSet.getTimestamp("team_updated_at");
            if (timestamp != null) {
                team.setUpdatedAt(timestamp.toLocalDateTime());
            }
            return team;
        }
        return null;
    }

    @SneakyThrows
    public static List<Team> mapRows(ResultSet resultSet) {
        List<Team> teams = new ArrayList<>();
        while (resultSet.next()) {
            Team team = new Team();
            team.setId(resultSet.getLong("team_id"));
            if (!resultSet.wasNull()) {
                team.setName(resultSet.getString("team_name"));
                team.setLogo(resultSet.getString("team_logo"));
                team.setCreatedAt(resultSet.getTimestamp("team_created_at").toLocalDateTime());
                Timestamp timestamp = resultSet.getTimestamp("team_updated_at");
                if (timestamp != null) {
                    team.setUpdatedAt(timestamp.toLocalDateTime());
                }
                teams.add(team);
            }
        }
        return teams;
    }
}
