package com.marsRoverApi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MarsPhoto {
    private Long id;
    private Integer sol;
    @JsonProperty("camera")
    private MarsCamera marsCamera;
    @JsonProperty("img_src")
    private String imgSrc;
    @JsonProperty("earth_date")
    private String earthDate;
    @JsonProperty("rover")
    private MarsRover marsRover;

    @Override
    public String toString() {
        return "MarsPhoto{" +
                "id=" + id +
                ", sol=" + sol +
                ", marsCamera=" + marsCamera +
                ", imgSrc='" + imgSrc + '\'' +
                ", earthDate='" + earthDate + '\'' +
                ", marsRover=" + marsRover +
                '}';
    }
}
