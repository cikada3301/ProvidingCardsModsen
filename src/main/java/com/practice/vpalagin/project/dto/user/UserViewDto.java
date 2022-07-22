package com.practice.vpalagin.project.dto.user;

import com.practice.vpalagin.project.model.enums.Access;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserViewDto {

    private String userName;

    private Access access;
}
