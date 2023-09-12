package com.challenge.lunch_randomizer.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChoicesListResponseDto {
    private String restaurantName;
    private String locationLink;
}
