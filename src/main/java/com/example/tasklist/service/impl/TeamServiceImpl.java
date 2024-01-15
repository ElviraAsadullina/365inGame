package com.example.tasklist.service.impl;

import com.example.tasklist.domain.exception.ResourceNotFoundException;
import com.example.tasklist.domain.team.Team;
import com.example.tasklist.repository.TeamRepository;
import com.example.tasklist.service.TeamService;
import com.example.tasklist.web.dto.team.TeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;

    @Override
    @Transactional(readOnly = true)
    public Team getById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Team not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Team> getAllByUserId(Long userId) {
        return teamRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public Team update(Team team) {
        teamRepository.update(team);
        return team;
    }

    @Override
    @Transactional
    public Team create(Team team) {
        teamRepository.create(team);
        return team;
    }

    @Override
    @Transactional
    public Team assignToUser(Long teamId, Long userId) {
        teamRepository.assignToUserById(teamId, userId);
        return getById(teamId);
    }

    @Override
    public void delete(Long id) {
        teamRepository.delete(id);
    }
}
