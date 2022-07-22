package com.practice.vpalagin.project.converter.impl;

import com.practice.vpalagin.project.converter.CompanyDtoConverter;
import com.practice.vpalagin.project.dto.company.CompanyDto;
import com.practice.vpalagin.project.model.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyDtoConverterImpl implements CompanyDtoConverter {

    @Override
    public Company convertFromCompanyDto(CompanyDto companyDto) {
        return Company.builder()
                .name(companyDto.getName())
                .admin(companyDto.getAdmin())
                .access(companyDto.getAccess())
                .accountant(companyDto.getAccountant())
                .users(companyDto.getUsers())
                .build();
    }

    @Override
    public CompanyDto convertToCompanyDto(Company company) {
        return CompanyDto.builder()
                .name(company.getName())
                .admin(company.getAdmin())
                .access(company.getAccess())
                .accountant(company.getAccountant())
                .users(company.getUsers())
                .build();
    }
}
