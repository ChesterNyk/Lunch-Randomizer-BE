package com.challenge.lunch_randomizer.dto.response;

import lombok.Data;

@Data
public class PaginatedResponseEnvelope<T> extends ResponseEnvelope<T>{
    private int totalPages;
}
