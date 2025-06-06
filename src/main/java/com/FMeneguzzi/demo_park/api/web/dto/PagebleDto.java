package com.FMeneguzzi.demo_park.api.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class PagebleDto {
    private List content = new ArrayList();
    private boolean first;
    private boolean last;
    @JsonProperty("Page")
    private int number;
    private int size;
    @JsonProperty("pageElements")
    private int numberOfElements;
    private int totalPages;
    private int totalElements;
}
