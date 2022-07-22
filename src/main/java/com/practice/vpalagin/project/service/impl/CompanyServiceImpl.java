package com.practice.vpalagin.project.service.impl;

import com.practice.vpalagin.project.converter.CompanyDtoConverter;
import com.practice.vpalagin.project.dto.company.CompanyDto;
import com.practice.vpalagin.project.exception.NotAuthorizedException;
import com.practice.vpalagin.project.model.Company;
import com.practice.vpalagin.project.model.User;
import com.practice.vpalagin.project.model.enums.Access;
import com.practice.vpalagin.project.repository.CompanyRepository;
import com.practice.vpalagin.project.repository.UserRepository;
import com.practice.vpalagin.project.security.userDetails.JwtUser;
import com.practice.vpalagin.project.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final CompanyDtoConverter companyDtoConverter;

    @Override
    @Transactional(readOnly = true)
    public List<Company> get(JwtUser jwtUser) {
        validateUserRole(jwtUser);
        return (List<Company>) companyRepository.findAll();
    }

    @Override
    @Transactional
    public void add(CompanyDto companyDto, JwtUser jwtUser) {
        validateUserRole(jwtUser);
        Company company = companyDtoConverter.convertFromCompanyDto(companyDto);
        companyRepository.save(company);
    }

    @Override
    @Transactional
    public void banCompany(Long id, JwtUser jwtUser) {
        validateUserRole(jwtUser);
        Company company = companyRepository.findById(id).get();
        company.setAccess(Access.DISABLED);
        company.getUsers()
                .forEach(u -> u.setAccess(Access.DISABLED));

    }

    @Override
    @Transactional
    public void addUser(Long id, Long userId, JwtUser jwtUser) {
        validateUserRole(jwtUser);
        Company company = companyRepository.findById(id).get();
        User user = userRepository.findById(userId).get();
        company.getUsers().add(user);
    }

    private void validateUserRole(JwtUser jwtUser) {
        if (!jwtUser.getUser().getRole().name().equals("ADMIN")) {
            throw new NotAuthorizedException("Access denied");
        }
    }
}
