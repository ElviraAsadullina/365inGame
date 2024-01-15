package com.example.tasklist.web.controller;

import com.example.tasklist.domain.team.Team;
import com.example.tasklist.service.TeamService;
import com.example.tasklist.web.dto.team.TeamDto;
import com.example.tasklist.web.dto.validation.OnCreate;
import com.example.tasklist.web.dto.validation.OnUpdate;
import com.example.tasklist.web.mappers.TeamMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
@Validated
@Tag(name = "Team Controller", description = "Team API")

public class TeamController {

    private final TeamService teamService;

    private final TeamMapper teamMapper;


    @PutMapping
    public TeamDto update(@Validated(OnUpdate.class)
                              @RequestBody TeamDto dto) {
        Team team = teamMapper.toEntity(dto);
        Team updatedTeam = teamService.update(team);
        return teamMapper.toDto(updatedTeam);
    }

    @PostMapping()
    public TeamDto createTeam(@Validated(OnCreate.class)
                              @RequestBody TeamDto dto) {
        Team team = teamMapper.toEntity(dto);
        Team createdTeam = teamService.create(team);
        return teamMapper.toDto(createdTeam);
    }

    @GetMapping("/{id}")
    public TeamDto getById(@PathVariable Long id) {
        Team team = teamService.getById(id);
        return teamMapper.toDto(team);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        teamService.delete(id);
    }
}
