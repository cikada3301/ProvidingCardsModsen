package com.practice.vpalagin.project.service;

import com.practice.vpalagin.project.dto.company.CompanyDto;
import com.practice.vpalagin.project.model.Company;
import com.practice.vpalagin.project.security.userDetails.JwtUser;

import java.util.List;

public interface CompanyService {
    List<Company> get(JwtUser jwtUser);
    void add(CompanyDto companyDto, JwtUser jwtUser);
    void banCompany(Long id, JwtUser jwtUser);
    void addUser(Long id, Long userId, JwtUser jwtUser);
}
