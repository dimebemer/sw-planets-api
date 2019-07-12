package com.starwars.swplanetsapi.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class HateoasWrapperDTO<T> {
    private int count;
    private String next;
    private String previous;
    private List<T> results;
}
