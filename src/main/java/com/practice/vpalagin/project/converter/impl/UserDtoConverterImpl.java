package com.practice.vpalagin.project.converter.impl;

import com.practice.vpalagin.project.converter.UserDtoConverter;
import com.practice.vpalagin.project.dto.user.UserMutationDto;
import com.practice.vpalagin.project.dto.user.UserViewDto;
import com.practice.vpalagin.project.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverterImpl implements UserDtoConverter {
    @Override
    public User convertFromUserMutationDto(UserMutationDto userMutationDto) {
        return User.builder()
                .role(userMutationDto.getRole())
                .userName(userMutationDto.getUserName())
                .password(userMutationDto.getPassword())
                .access(userMutationDto.getAccess())
                .build();
    }

    @Override
    public UserViewDto convertToUserViewDto(User user) {
        return UserViewDto.builder()
                .userName(user.getUserName())
                .access(user.getAccess())
                .build();
    }
}
