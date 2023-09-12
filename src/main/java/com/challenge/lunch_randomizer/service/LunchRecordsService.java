package com.challenge.lunch_randomizer.service;

import com.challenge.lunch_randomizer.dto.request.LunchOptionsRequestDto;
import com.challenge.lunch_randomizer.dto.request.RecordsIdRequestDto;
import com.challenge.lunch_randomizer.dto.response.LunchRecordDto;
import com.challenge.lunch_randomizer.dto.response.PaginatedResponseEnvelope;
import com.challenge.lunch_randomizer.dto.response.ResponseEnvelope;

import java.util.List;

public interface LunchRecordsService {
    // Get all lunch records from DB
    PaginatedResponseEnvelope<LunchRecordDto> getAllLunchRecords();

    // delete records based on selected Ids
    List<LunchRecordDto> deleteLunchRecords(List<RecordsIdRequestDto> requestBody);

    // update & randomize options
    LunchRecordDto randomizeLunchOptions(List<LunchOptionsRequestDto> requestBody);

}
