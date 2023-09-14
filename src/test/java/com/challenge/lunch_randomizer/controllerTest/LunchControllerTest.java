package com.challenge.lunch_randomizer.controllerTest;

import com.challenge.lunch_randomizer.controller.LunchController;
import com.challenge.lunch_randomizer.dto.request.LunchOptionsRequestDto;
import com.challenge.lunch_randomizer.dto.request.RecordsIdRequestDto;
import com.challenge.lunch_randomizer.dto.response.ChoicesListResponseDto;
import com.challenge.lunch_randomizer.dto.response.CommonResponseBody;
import com.challenge.lunch_randomizer.dto.response.LunchRecordDto;
import com.challenge.lunch_randomizer.service.LunchRecordsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LunchController.class)
public class LunchControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LunchRecordsService lunchRecordsService;

    @Test
    void testGetPastLunchRecords () throws Exception {
        // Mock data for service response
        LunchRecordDto mockRecord = new LunchRecordDto();
        mockRecord.setLunchRecordId(1L);

        List<LunchRecordDto> mockResponseList = new ArrayList<>();
        mockResponseList.add(mockRecord);

        // Mock behaviour of service
        when(lunchRecordsService.getAllLunchRecords()).thenReturn(mockResponseList);

        // Perform the actual request
        mockMvc.perform(get("/lunch/allRecords")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", equalTo(CommonResponseBody.KEY_RESULT_SUCCESS)))
                .andExpect(jsonPath("$.data.list", hasSize(1)))
                .andExpect(jsonPath("$.data.list[0].lunchRecordId", equalTo(1)));
    }

    @Test
    void testDeleteLunchRecords() throws Exception {
        // Define sample request body
        List<RecordsIdRequestDto> requestBody = new ArrayList<>();

        RecordsIdRequestDto requestDto = new RecordsIdRequestDto();
        requestDto.setRecordsId("1");
        requestBody.add(requestDto);

        // Define sample response
        List<LunchRecordDto> response = new ArrayList<>();

        //Mock behaviour to service
        when(lunchRecordsService.deleteLunchRecords(requestBody)).thenReturn(response);

        // Perform the actual request
        mockMvc.perform(post("/lunch/deleteRecords").contentType(MediaType.APPLICATION_JSON).content(asJsonString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(CommonResponseBody.KEY_RESULT_SUCCESS)))
                .andExpect(jsonPath("$.data.list", hasSize(0)));
    }

    @Test
    void testRandomizeOptions() throws Exception {
        // Define sample request Body
        List<LunchOptionsRequestDto> requestBody = new ArrayList<>();

        LunchOptionsRequestDto requestDto = new LunchOptionsRequestDto();
        requestDto.setOptions("Restaurant 1");
        requestBody.add(requestDto);

        LunchOptionsRequestDto requestDto1 = new LunchOptionsRequestDto();
        requestDto1.setOptions("Restaurant 2");
        requestBody.add(requestDto1);

        // Define sample Response
        LunchRecordDto response = new LunchRecordDto();
        List<ChoicesListResponseDto> optionList = new ArrayList<>();

        ChoicesListResponseDto optionsResponse = new ChoicesListResponseDto();
        optionsResponse.setRestaurantName("Restaurant 1");
        optionList.add(optionsResponse);

        ChoicesListResponseDto optionsResponse1 = new ChoicesListResponseDto();
        optionsResponse.setRestaurantName("Restaurant 2");
        optionList.add(optionsResponse1);
        response.setOptionsList(optionList);

        //Mock behaviour to service
        when(lunchRecordsService.randomizeLunchOptions(requestBody)).thenReturn(response);

        // perform actual request
        mockMvc.perform(post("/lunch/randomiseOptions").contentType(MediaType.APPLICATION_JSON).content(asJsonString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(CommonResponseBody.KEY_RESULT_SUCCESS)))
                .andExpect(jsonPath("$.data.list", notNullValue()))
                .andExpect(jsonPath("$.data.list.optionsList", hasSize(2)));
    }

    // Convert Object to string
    private static String asJsonString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
