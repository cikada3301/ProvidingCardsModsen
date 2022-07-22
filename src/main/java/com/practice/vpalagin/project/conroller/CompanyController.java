package com.practice.vpalagin.project.conroller;

import com.practice.vpalagin.project.dto.company.CompanyDto;
import com.practice.vpalagin.project.model.Company;
import com.practice.vpalagin.project.security.userDetails.JwtUser;
import com.practice.vpalagin.project.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public ResponseEntity<?> get(@AuthenticationPrincipal JwtUser jwtUser) {
        List<Company> companyList = companyService.get(jwtUser);
        return ResponseEntity.ok(companyList);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody CompanyDto companyDto, @AuthenticationPrincipal JwtUser jwtUser) {
        companyService.add(companyDto, jwtUser);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> banCompany(@PathVariable Long id, @AuthenticationPrincipal JwtUser jwtUser){
        companyService.banCompany(id, jwtUser);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/")
    public ResponseEntity<?> addUser(@PathVariable Long id, @RequestParam Long userId, @AuthenticationPrincipal JwtUser jwtUser){
        companyService.addUser(id, userId, jwtUser);
        return ResponseEntity.ok().build();
    }
}
