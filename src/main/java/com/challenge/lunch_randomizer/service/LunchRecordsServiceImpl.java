package com.challenge.lunch_randomizer.service;

import com.challenge.lunch_randomizer.dto.request.LunchOptionsRequestDto;
import com.challenge.lunch_randomizer.dto.request.RecordsIdRequestDto;
import com.challenge.lunch_randomizer.dto.response.ChoicesListResponseDto;
import com.challenge.lunch_randomizer.dto.response.LunchRecordDto;
import com.challenge.lunch_randomizer.dto.response.PaginatedResponseEnvelope;
import com.challenge.lunch_randomizer.dto.response.ResponseEnvelope;
import com.challenge.lunch_randomizer.model.Choices;
import com.challenge.lunch_randomizer.model.LunchRecords;
import com.challenge.lunch_randomizer.repository.ChoicesRepository;
import com.challenge.lunch_randomizer.repository.LunchRecordsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Log4j2
public class LunchRecordsServiceImpl implements  LunchRecordsService {
    @Autowired
    LunchRecordsRepository lunchRecordsRepository;

    @Autowired
    ChoicesRepository choicesRepository;

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
        log.info("BEGIN | RANDOMIZE LUNCH OPTIONS");
        LunchRecordDto response = new LunchRecordDto();

        // Randomise list and get value
        Random random = new Random();
        LunchOptionsRequestDto decision = (request.get(random.nextInt(request.size())));
        log.info("FINAL DECISION : " + decision.getOptions());

        // Save into DB
        saveLunchChoiceRecords(decision.getOptions(), request);

        // save final location + choices into db for response
        response.setFinalLocation(decision.getOptions());
        response.setCreatedDateTime(new Date());

        List<ChoicesListResponseDto> optionsList = new ArrayList<>();
        request.forEach(requestOptions -> {
            ChoicesListResponseDto options = new ChoicesListResponseDto();
            options.setRestaurantName(requestOptions.getOptions());

            optionsList.add(options);
        });

        response.setOptionsList(optionsList);
        log.info("END | RANDOMIZE LUNCH OPTIONS");
        return response;
    }

    private void saveLunchChoiceRecords (String finalLocation, List<LunchOptionsRequestDto> request) {
        log.info("BEGIN | SAVING LUNCH + CHOICES RECORDS");

        LunchRecords lunchRecords = new LunchRecords();
        lunchRecords.setFinalLocation(finalLocation);
        lunchRecords.setCreatedDateTime(new Date());

        lunchRecordsRepository.save(lunchRecords);

        request.forEach(requestOptions -> {
            Choices choices = new Choices();

            choices.setLunchRecords(lunchRecords);
            choices.setRestaurantName(requestOptions.getOptions());
            choices.setCreatedDateTime(new Date());

            choicesRepository.save(choices);
        });

        log.info("END | SAVING LUNCH + CHOICES RECORDS");
    }
}
