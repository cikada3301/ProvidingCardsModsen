package com.practice.vpalagin.project.dto.card;

import com.practice.vpalagin.project.model.User;
import com.practice.vpalagin.project.model.enums.Access;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Builder
public class CardDto {

    @NotEmpty(message = "serialNumber could not be empty")
    @Min(value = 10, message = "serialNumber min size 10")
    private Long serialNumber;

    @NotEmpty(message = "typeCard could not be empty")
    private String typeCard;

    @NotEmpty(message = "validityDate could not be empty")
    private Date validityDate;

    @NotEmpty(message = "department could not be empty")
    private String department;

    @NotEmpty(message = "access could not be empty")
    private Access access;

    @NotEmpty(message = "owner could not be empty")
    private User owner;
}
