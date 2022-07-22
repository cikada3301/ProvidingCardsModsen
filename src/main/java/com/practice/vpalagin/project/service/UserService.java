package com.practice.vpalagin.project.service;

import com.practice.vpalagin.project.dto.user.UserMutationDto;
import com.practice.vpalagin.project.dto.user.UserAuthenticationDto;
import com.practice.vpalagin.project.dto.user.UserViewDto;
import com.practice.vpalagin.project.security.userDetails.JwtUser;

import java.util.List;

public interface UserService {
    void save(UserAuthenticationDto user, JwtUser jwtUser);
    void saveAccountant(UserMutationDto userMutationDto, JwtUser jwtUser);
    List<UserViewDto> getAccountants(JwtUser jwtUser);
    void banUser(Long id, JwtUser jwtUser);
}
