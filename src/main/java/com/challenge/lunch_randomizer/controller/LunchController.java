package com.challenge.lunch_randomizer.controller;

import com.challenge.lunch_randomizer.dto.request.LunchOptionsRequestDto;
import com.challenge.lunch_randomizer.dto.request.RecordsIdRequestDto;
import com.challenge.lunch_randomizer.dto.response.CommonResponseBody;
import com.challenge.lunch_randomizer.dto.response.LunchRecordDto;
import com.challenge.lunch_randomizer.dto.response.PaginatedResponseEnvelope;
import com.challenge.lunch_randomizer.model.LunchRecords;
import com.challenge.lunch_randomizer.service.LunchRecordsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("lunch")
public class LunchController {
    @Autowired
    LunchRecordsService lunchRecordsService;

    // Get all past records of randomize lunch options
    @RequestMapping(method = RequestMethod.GET, path = "/allRecords", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPastLunchRecords() {
        log.info("BEGIN | Getting all past lunch records");

        CommonResponseBody responseBody = new CommonResponseBody();

        try {
            // Get all records from DB
        } catch (Exception e ) {
            log.error("ERROR : " + e);
            responseBody.setResult(CommonResponseBody.KEY_RESULT_FAIL);
            responseBody.setErrorCode("500");
            responseBody.setErrorMessage("Technical Issues");
        } finally {
            log.info("END | Get all past lunch records");
        }

        return new ResponseEntity<>(responseBody.getMap(), HttpStatus.OK);
    }

    // Clear old records of lunch options
    @RequestMapping(method = RequestMethod.POST, path="/deleteRecords", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteLunchRecords(@RequestBody List<RecordsIdRequestDto> requestBody) {
        log.info("BEGIN | Deleting selected lunch records");
        CommonResponseBody responseBody = new CommonResponseBody();

        try {
            // delete from DB the records passed in RequestBody

        } catch (Exception e ) {
            log.error("ERROR : " + e);
            responseBody.setResult(CommonResponseBody.KEY_RESULT_FAIL);
            responseBody.setErrorCode("500");
            responseBody.setErrorMessage("Technical Issues");
        } finally {
            log.info("END | Deleting selected lunch records");
        }

        return new ResponseEntity<>(responseBody.getMap(), HttpStatus.OK);
    }

    // Submit records to choose a lunch location
    @RequestMapping(method = RequestMethod.POST,path = "/randomiseOptions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> randomizeOptions(@RequestBody List<LunchOptionsRequestDto> requestBody) {
        log.info("BEGIN | Randomize from selected lunch records");
        CommonResponseBody responseBody = new CommonResponseBody();

        try {
            // Pre-condition checks
            log.info("BEGIN | PRE-CONDITION CHECKS ");

            log.info("END | PRE-CONDITION CHECKS ");

            // randomise options passed from request body
            LunchRecordDto response = lunchRecordsService.randomizeLunchOptions(requestBody);

            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("list", response);

            responseBody.setResult(CommonResponseBody.KEY_RESULT_SUCCESS);
            responseBody.setData(dataMap);
        } catch (Exception e ) {
            log.error("ERROR : " + e);
            responseBody.setResult(CommonResponseBody.KEY_RESULT_FAIL);
            responseBody.setErrorCode("500");
            responseBody.setErrorMessage("Technical Issues");
        } finally {
            log.info("END | Randomize from selected lunch records");
        }

        return new ResponseEntity<>(responseBody.getMap(), HttpStatus.OK);
    }
}
