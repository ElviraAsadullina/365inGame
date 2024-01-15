package com.example.tasklist.web.dto.user;


import com.example.tasklist.web.dto.validation.OnCreate;
import com.example.tasklist.web.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data
@Schema(description = "User DTO")
public class UserDto {

    @Schema(description = "User id", example = "1")
    @NotNull(message = "id cannot be null", groups = OnUpdate.class)
    private Long id;

    @Schema(description = "User name", example = "John Doe")
    @NotNull(message = "name cannot be null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "name cannot be longer than 255 characters", groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @Schema(description = "User email", example = "johndoe@gmail.com")
    @NotNull(message = "username cannot be null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "username cannot be longer than 255 characters", groups = {OnCreate.class, OnUpdate.class})
    private String username;

    @Schema(description = "User encryted password", example = "$2a$10$Xl0yhvzLIaJCDdKBS0Lld.ksK7c2Zytg/ZKFdtIYYQUv8rUfvCR4W")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // то есть пароль не вернется, он может быть только записан
    @NotNull(message = "password cannot be null", groups = {OnCreate.class, OnUpdate.class})
    private String password;

    @Schema(description = "User password confirmation", example = "$2a$10$Xl0yhvzLIaJCDdKBS0Lld.ksK7c2Zytg/ZKFdtIYYQUv8rUfvCR4W")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // то есть подтверждение пароля не вернется, он может быть только записан
    @NotNull(message = "password confirmation cannot be null", groups = OnCreate.class)
    private String passwordConfirmation;

}
