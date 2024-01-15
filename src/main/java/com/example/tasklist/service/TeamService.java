package com.example.tasklist.service;


import com.example.tasklist.domain.team.Team;

import java.util.List;

public interface TeamService {

    Team getById(Long id);
    List<Team> getAllByUserId(Long userId);
    Team update(Team team);
    Team create(Team team);

    Team assignToUser(Long teamId, Long userId);
    void delete(Long id);
}
