package com.challenge.lunch_randomizer.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class ResponseEnvelope<T> {
    private List<T> list;
    private long totalItems;

    public ResponseEnvelope(List<T> list, Integer totalItems) {
        this.list = list;
        this.totalItems = totalItems;
    }
}
