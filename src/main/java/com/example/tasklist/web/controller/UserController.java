package com.example.tasklist.web.controller;

import com.example.tasklist.domain.task.Task;
import com.example.tasklist.domain.team.Team;
import com.example.tasklist.domain.user.User;
import com.example.tasklist.service.TaskService;
import com.example.tasklist.service.TeamService;
import com.example.tasklist.service.UserService;
import com.example.tasklist.web.dto.task.TaskDto;
import com.example.tasklist.web.dto.team.TeamDto;
import com.example.tasklist.web.dto.user.UserDto;
import com.example.tasklist.web.dto.validation.OnCreate;
import com.example.tasklist.web.dto.validation.OnUpdate;
import com.example.tasklist.web.mappers.TaskMapper;
import com.example.tasklist.web.mappers.TeamMapper;
import com.example.tasklist.web.mappers.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
@Tag(name = "User Controller", description = "User API")
public class UserController {

    private final UserService userService;
    private final TaskService taskService;
    private final TeamService teamService;


    private final UserMapper userMapper;
    private final TaskMapper taskMapper;
    private final TeamMapper teamMapper;

    @PutMapping
    @Operation(summary = "Update user")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#dto.id)")
    public UserDto update(@Validated(OnUpdate.class)
                              @RequestBody UserDto dto) {
        User user = userMapper.toEntity(dto);
        User updatedUser = userService.update(user);
        return userMapper.toDto(updatedUser);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public UserDto getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by id")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public void deleteById(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}/tasks")
    @Operation(summary = "Get tasks by user id")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public List<TaskDto> getTasksByUserId(@PathVariable Long id) {
        List<Task> tasks = taskService.getAllByUserId(id);
        return taskMapper.toDto(tasks);
    }

    @PostMapping("/{id}/tasks")
    @Operation(summary = "Create task")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public TaskDto createTask(@PathVariable Long id,
                              @Validated(OnCreate.class)
                              @RequestBody TaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        Task createdTask = taskService.create(task, id);
        return taskMapper.toDto(createdTask);
    }

    @PostMapping("/{user_id}/teams/{team_id}")
    @Operation(summary = "Assign user to team")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#dto.id)")
    public TeamDto assignTeam(@PathVariable Long user_id,
                              @PathVariable Long team_id) {
        Team updatedTeam = teamService.assignToUser(team_id, user_id);
        return teamMapper.toDto(updatedTeam);
    }
}

