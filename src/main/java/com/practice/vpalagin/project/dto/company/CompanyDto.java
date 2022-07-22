package com.practice.vpalagin.project.dto.company;

import com.practice.vpalagin.project.model.User;
import com.practice.vpalagin.project.model.enums.Access;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
@Data
public class CompanyDto {

    @NotEmpty(message = "name could not be null")
    private String name;

    @NotEmpty(message = "admin could not be null")
    private User admin;

    @NotEmpty(message = "access could not be null")
    private Access access;

    @NotEmpty(message = "accountant could not be null")
    private User accountant;

    private List<User> users;
}
