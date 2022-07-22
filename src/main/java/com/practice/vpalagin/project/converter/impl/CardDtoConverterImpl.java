package com.practice.vpalagin.project.converter.impl;

import com.practice.vpalagin.project.converter.CardDtoConverter;
import com.practice.vpalagin.project.dto.card.CardDto;
import com.practice.vpalagin.project.dto.card.TruncateCardDto;
import com.practice.vpalagin.project.model.Card;
import org.springframework.stereotype.Component;

@Component
public class CardDtoConverterImpl implements CardDtoConverter {
    @Override
    public Card convertFromDto(CardDto cardDto) {
        return Card.builder()
                .owner(cardDto.getOwner())
                .typeCard(cardDto.getTypeCard())
                .serialNumber(cardDto.getSerialNumber())
                .validityDate(cardDto.getValidityDate())
                .department(cardDto.getDepartment())
                .access(cardDto.getAccess())
                .build();
    }

    @Override
    public CardDto convertToDto(Card card) {
        return CardDto.builder()
                .owner(card.getOwner())
                .typeCard(card.getTypeCard())
                .serialNumber(card.getSerialNumber())
                .validityDate(card.getValidityDate())
                .department(card.getDepartment())
                .access(card.getAccess())
                .build();
    }

    @Override
    public TruncateCardDto convertToTruncateDto(Card card) {
        return TruncateCardDto.builder()
                .department(card.getDepartment())
                .typeCard(card.getTypeCard())
                .access(card.getAccess())
                .build();
    }
}
