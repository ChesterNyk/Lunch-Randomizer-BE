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
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Log4j2
public class LunchRecordsServiceImpl implements  LunchRecordsService {
    @Autowired
    LunchRecordsRepository lunchRecordsRepository;

    @Autowired
    ChoicesRepository choicesRepository;

    @Override
    public List<LunchRecordDto> getAllLunchRecords () {
        log.info("BEGIN | Get all lunch records ");
        // to get records from repository
        List<LunchRecords> lunchRecordsList = lunchRecordsRepository.findAll();
        List<LunchRecordDto> recordsList = new ArrayList<>();

        lunchRecordsList.forEach(record -> {
            LunchRecordDto lunchRecords = new LunchRecordDto();
            List<ChoicesListResponseDto> choicesResponseList = findAllChoicesMatchingRecord(record.getLunchRecordId());

            lunchRecords.setLunchRecordId(record.getLunchRecordId());
            lunchRecords.setOptionsList(choicesResponseList);
            lunchRecords.setFinalLocation(record.getFinalLocation());
            lunchRecords.setCreatedDateTime(record.getCreatedDateTime());

            recordsList.add(lunchRecords);
        });

        return recordsList;
    }

    @Override
    @Transactional
    public List<LunchRecordDto> deleteLunchRecords(List<RecordsIdRequestDto> request) {
        log.info("BEGIN | DELETE LUNCH RECORDS ");
        List<LunchRecordDto> response = new ArrayList<>();

         // Loop through request body
        request.forEach(req -> {
            // find records in DB matching request IDs
            Optional<LunchRecords> lunchRecordsOptional = lunchRecordsRepository.findByLunchRecordId(Long.valueOf(req.getRecordsId()));

            if (lunchRecordsOptional.isPresent()) {
                LunchRecords lunchRecords = lunchRecordsOptional.get();

                LunchRecordDto records = new LunchRecordDto();

                // Setting response to provide feedback on what was deleted
                List<ChoicesListResponseDto> choicesResponseList = findAllChoicesMatchingRecord(lunchRecords.getLunchRecordId());
                records.setOptionsList(choicesResponseList);
                records.setLunchRecordId(lunchRecords.getLunchRecordId());
                records.setFinalLocation(lunchRecords.getFinalLocation());
                records.setCreatedDateTime(lunchRecords.getCreatedDateTime());

                response.add(records);

                // Delete from DB
                deleteFromChoices(records.getLunchRecordId());
                deleteFromLunchRecords(records.getLunchRecordId());
            }
        });

        log.info("END | DELETE LUNCH RECORDS");
        return response;
    }

    @Override
    public LunchRecordDto randomizeLunchOptions(List<LunchOptionsRequestDto> request) {
        log.info("BEGIN | RANDOMIZE LUNCH OPTIONS");
        LunchRecordDto response = new LunchRecordDto();

        // Randomise list and get value
        Random random = new Random();
        LunchOptionsRequestDto decision = (request.get(random.nextInt(request.size())));

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

    private List<ChoicesListResponseDto> findAllChoicesMatchingRecord(Long recordID) {
        List<Choices> choicesList = choicesRepository.findAllByLunchRecordsLunchRecordId(recordID);

        List<ChoicesListResponseDto> choicesResponseList = new ArrayList<>();

        choicesList.forEach(options -> {
            ChoicesListResponseDto choice = new ChoicesListResponseDto();

            choice.setRestaurantName(options.getRestaurantName());

            choicesResponseList.add(choice);
        });

        return choicesResponseList;
    }

    private void deleteFromChoices(Long recordId) {
        choicesRepository.deleteByLunchRecordsLunchRecordId(recordId);
    }

    private void deleteFromLunchRecords(Long recordId) {
        lunchRecordsRepository.deleteByLunchRecordId(recordId);
    }
}
