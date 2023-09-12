package com.challenge.lunch_randomizer.service;

import com.challenge.lunch_randomizer.dto.request.LunchOptionsRequestDto;
import com.challenge.lunch_randomizer.dto.request.RecordsIdRequestDto;
import com.challenge.lunch_randomizer.dto.response.LunchRecordDto;
import com.challenge.lunch_randomizer.dto.response.PaginatedResponseEnvelope;
import com.challenge.lunch_randomizer.dto.response.ResponseEnvelope;
import com.challenge.lunch_randomizer.model.LunchRecords;
import com.challenge.lunch_randomizer.repository.LunchRecordsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class LunchRecordsServiceImpl implements  LunchRecordsService {
    @Autowired
    LunchRecordsRepository lunchRecordsRepository;

    @Override
    public PaginatedResponseEnvelope<LunchRecordDto> getAllLunchRecords () {
        log.info("BEGIN | Get all lunch records ");

        PaginatedResponseEnvelope<LunchRecordDto> responseBody = new PaginatedResponseEnvelope<>();

        // to get records from repository


        // map entity from DB to Dto


        return responseBody;
    }

    @Override
    public List<LunchRecordDto> deleteLunchRecords(List<RecordsIdRequestDto> request) {
        List<LunchRecordDto> response = new ArrayList<>();
         LunchRecordDto records = new LunchRecordDto();

         // Loop through request body

        // find records in DB matching request IDs

        // Save records found into response DTOs

        // delete from DB

        response.add(records);

        return response;
    }

    @Override
    public LunchRecordDto randomizeLunchOptions(List<LunchOptionsRequestDto> request) {
        LunchRecordDto response = new LunchRecordDto();

        // Randomise list and get value

        // save value into DB

        // return value gotten from randomization

        return response;
    }
}
