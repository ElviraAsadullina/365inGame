package com.example.tasklist.domain.team;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Team {

    private Long id;
    private String name;
    private String logo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
