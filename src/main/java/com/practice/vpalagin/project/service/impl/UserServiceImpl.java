package com.practice.vpalagin.project.service.impl;

import com.practice.vpalagin.project.converter.UserDtoConverter;
import com.practice.vpalagin.project.dto.user.UserAuthenticationDto;
import com.practice.vpalagin.project.dto.user.UserMutationDto;
import com.practice.vpalagin.project.dto.user.UserViewDto;
import com.practice.vpalagin.project.exception.NotAuthorizedException;
import com.practice.vpalagin.project.exception.UserNotFoundException;
import com.practice.vpalagin.project.model.User;
import com.practice.vpalagin.project.model.enums.Access;
import com.practice.vpalagin.project.repository.UserRepository;
import com.practice.vpalagin.project.security.userDetails.JwtUser;
import com.practice.vpalagin.project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final UserDtoConverter userDtoConverter;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public UserDetails loadUserByUsername(String username) throws RuntimeException {
        Optional<User> user = userRepository.getByUserName(username);
        System.out.println(user);
        if (user.orElseThrow(() -> new UserNotFoundException("User not found")).getAccess().name().equals("DISABLED")) {
            throw new RuntimeException("You are banned");
        }
        return new JwtUser(user.orElseThrow(() -> new UserNotFoundException("User not found")));
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
    public void save(UserAuthenticationDto changedUser, JwtUser jwtUser) {
        User user = userRepository.getByUserName(jwtUser.getUsername()).get();
        user.setPassword(new BCryptPasswordEncoder().encode(changedUser.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserViewDto> getAccountants(JwtUser jwtUser) {
        validateUserRole(jwtUser);
        List<User> accountants = (List<User>) userRepository.findAll();
        return accountants.stream()
                .filter(a -> a.getRole().name().equals("ACCOUNTANT"))
                .map(userDtoConverter::convertToUserViewDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveAccountant(UserMutationDto userMutationDto, JwtUser jwtUser) {
        validateUserRole(jwtUser);
        User user = userDtoConverter.convertFromUserMutationDto(userMutationDto);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void banUser(Long id, JwtUser jwtUser) {
        User user = userRepository.findById(id).get();
        if (jwtUser.getUser().getRole().name().equals("ADMIN")) {
            user.setAccess(Access.DISABLED);
            userRepository.save(user);
        } else if (jwtUser.getUser().getRole().name().equals("ACCOUNTANT") && user.getRole().name().equals("EMPLOYEE")) {
            user.setAccess(Access.DISABLED);
            userRepository.save(user);
        }
    }

    private void validateUserRole(JwtUser jwtUser) {
        if (!jwtUser.getUser().getRole().name().equals("ADMIN")) {
            throw new NotAuthorizedException("Access denied");
        }
    }
}
