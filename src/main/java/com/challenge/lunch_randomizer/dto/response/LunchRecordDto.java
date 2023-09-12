package com.challenge.lunch_randomizer.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class LunchRecordDto {
    private Long lunchRecordId;
    private List<ChoicesListResponseDto> optionsList;
    private String finalLocation;
    private Date createdDateTime;
}
