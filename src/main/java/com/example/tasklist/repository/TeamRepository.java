package com.example.tasklist.repository;

import com.example.tasklist.domain.team.Team;

import java.util.List;
import java.util.Optional;

public interface TeamRepository {

    Optional<Team> findById(Long id);

    List<Team> findAllByUserId(Long userId);
    void assignToUserById(Long teamId, Long userId);

    void update(Team team);
    void create(Team team);

    void delete(Long id);
}
