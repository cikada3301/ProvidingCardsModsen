package com.practice.vpalagin.project.dto.card;

import com.practice.vpalagin.project.model.enums.Access;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TruncateCardDto {

    private String typeCard;
    private String department;
    private Access access;
}
