package com.practice.vpalagin.project.service.impl;

import com.practice.vpalagin.project.converter.CardDtoConverter;
import com.practice.vpalagin.project.dto.card.CardDto;
import com.practice.vpalagin.project.dto.card.TruncateCardDto;
import com.practice.vpalagin.project.exception.NotAuthorizedException;
import com.practice.vpalagin.project.model.Card;
import com.practice.vpalagin.project.model.Company;
import com.practice.vpalagin.project.repository.CardRepository;
import com.practice.vpalagin.project.repository.CompanyRepository;
import com.practice.vpalagin.project.security.userDetails.JwtUser;
import com.practice.vpalagin.project.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CompanyRepository companyRepository;
    private final CardDtoConverter cardDtoConverter;

    @Override
    @Transactional(readOnly = true)
    public List<CardDto> get(JwtUser jwtUser) {
        validateRole(jwtUser);

        List<Card> cards = (List<Card>) cardRepository.findAll();
        List<Company> companies = (List<Company>) companyRepository.findAll();
        Company company = companies.stream()
                .filter(c -> Objects.equals(c.getAccountant().getUserName(), jwtUser.getUsername()))
                .findFirst().get();

        return cards.stream()
                .filter(card -> company.getUsers().contains(card.getOwner()))
                .map(cardDtoConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CardDto> getMy(JwtUser jwtUser) {
        List<Card> cards = (List<Card>) cardRepository.findAll();
        return cards.stream()
                .filter(a -> Objects.equals(a.getOwner().getUserName(), jwtUser.getUser().getUserName()))
                .map(cardDtoConverter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TruncateCardDto> getOther(JwtUser jwtUser) {
        List<Card> cards = (List<Card>) cardRepository.findAll();
        return cards.stream()
                .map(cardDtoConverter::convertToTruncateDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void save(CardDto cardDto, JwtUser jwtUser) {
        validateRole(jwtUser);
        Card card = cardDtoConverter.convertFromDto(cardDto);
        cardRepository.save(card);
    }

    private void validateRole(JwtUser jwtUser) {
        if (!jwtUser.getUser().getRole().name().equals("ACCOUNTANT")) {
            throw new NotAuthorizedException("Access denied");
        }
    }
}
