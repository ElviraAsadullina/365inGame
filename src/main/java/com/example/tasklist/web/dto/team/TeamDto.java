package com.example.tasklist.web.dto.team;

import com.example.tasklist.web.dto.validation.OnCreate;
import com.example.tasklist.web.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class TeamDto {
    @NotNull(message = "id cannot be null", groups = OnUpdate.class)
    private Long id;
    @NotNull(message = "name cannot be null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "name cannot be longer than 255 characters", groups = {OnCreate.class, OnUpdate.class})
    private String name;
    @Length(max = 255, message = "logo cannot be longer than 255 characters", groups = {OnCreate.class, OnUpdate.class})
    private String logo;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime expirationDate;
}
