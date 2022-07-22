package com.practice.vpalagin.project.dto.user;

import com.practice.vpalagin.project.model.enums.Access;
import com.practice.vpalagin.project.model.enums.UserRole;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
public class UserMutationDto {

    @NotEmpty(message = "role could not be empty")
    private UserRole role;

    @NotEmpty(message = "username could not be empty")
    private String userName;

    @NotEmpty(message = "password could not be empty")
    @Size(min = 4, max = 20, message = "Please make a valid password")
    private String password;

    @NotEmpty(message = "access could not be empty")
    private Access access;
}
