package com.challenge.lunch_randomizer.controller;

import com.challenge.lunch_randomizer.dto.request.LunchOptionsRequestDto;
import com.challenge.lunch_randomizer.dto.request.RecordsIdRequestDto;
import com.challenge.lunch_randomizer.dto.response.CommonResponseBody;
import com.challenge.lunch_randomizer.dto.response.LunchRecordDto;
import com.challenge.lunch_randomizer.dto.response.PaginatedResponseEnvelope;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("lunch")
public class LunchController {

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
            // randomise options passed from request body

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
