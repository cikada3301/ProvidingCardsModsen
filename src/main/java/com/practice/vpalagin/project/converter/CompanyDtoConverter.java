package com.practice.vpalagin.project.converter;

import com.practice.vpalagin.project.dto.company.CompanyDto;
import com.practice.vpalagin.project.model.Company;

public interface CompanyDtoConverter {
    Company convertFromCompanyDto(CompanyDto companyDto);

    CompanyDto convertToCompanyDto(Company company);
}
