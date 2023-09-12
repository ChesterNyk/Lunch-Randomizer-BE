package com.challenge.lunch_randomizer.controller;

import com.challenge.lunch_randomizer.dto.response.CommonResponseBody;
import com.challenge.lunch_randomizer.dto.response.LunchRecordDto;
import com.challenge.lunch_randomizer.dto.response.PaginatedResponseEnvelope;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("lunch")
public class LunchController {

    // Get all past records of randomize lunch options
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
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

        return  new ResponseEntity<>(responseBody.getMap(), HttpStatus.OK);
    }
}
