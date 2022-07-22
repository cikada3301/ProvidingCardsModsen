package com.practice.vpalagin.project.converter;

import com.practice.vpalagin.project.dto.card.CardDto;
import com.practice.vpalagin.project.dto.card.TruncateCardDto;
import com.practice.vpalagin.project.model.Card;

public interface CardDtoConverter {
    Card convertFromDto(CardDto cardDto);
    CardDto convertToDto(Card card);
    TruncateCardDto convertToTruncateDto(Card card);
}
