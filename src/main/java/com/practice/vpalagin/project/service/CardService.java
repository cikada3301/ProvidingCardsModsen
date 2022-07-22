package com.practice.vpalagin.project.service;

import com.practice.vpalagin.project.dto.card.CardDto;
import com.practice.vpalagin.project.dto.card.TruncateCardDto;
import com.practice.vpalagin.project.security.userDetails.JwtUser;

import java.util.List;

public interface CardService {
    List<CardDto> get(JwtUser jwtUser);

    List<TruncateCardDto> getOther(JwtUser jwtUser);

    void save(CardDto cardDto, JwtUser jwtUser);

    List<CardDto> getMy(JwtUser jwtUser);
}
