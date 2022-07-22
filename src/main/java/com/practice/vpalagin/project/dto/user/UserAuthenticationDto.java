package com.practice.vpalagin.project.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class UserAuthenticationDto {

    @NotBlank(message = "Please fill out the required login")
    @Size(min = 4, message = "Please make sure you are using a valid login")
    private String userName;

    @NotBlank(message = "Please fill out the required field")
    @Size(min = 4, max = 20, message = "Please make sure you are using a valid password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
