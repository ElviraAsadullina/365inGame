package com.example.tasklist.web.mappers;

import com.example.tasklist.domain.team.Team;
import com.example.tasklist.web.dto.team.TeamDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    TeamDto toDto(Team team);
    List<TeamDto> toDto(List<Team> teams);

    Team toEntity(TeamDto dto);
}
