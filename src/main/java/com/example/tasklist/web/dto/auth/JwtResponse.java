package com.example.tasklist.web.dto.auth;

import lombok.Data;

@Data
public class JwtResponse {

    //тут нет смысла в валидации - мы его ен принимаем, а сами создаем
    private Long id;
    private String username;
    private String accessToken;
    private String refreshToken;
}
