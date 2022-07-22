package com.practice.vpalagin.project.converter;

import com.practice.vpalagin.project.dto.user.UserMutationDto;
import com.practice.vpalagin.project.dto.user.UserViewDto;
import com.practice.vpalagin.project.model.User;

public interface UserDtoConverter {
    User convertFromUserMutationDto(UserMutationDto userMutationDto);
    UserViewDto convertToUserViewDto(User user);
}
