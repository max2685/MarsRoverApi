package com.marsRoverApi.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MarsRoverApiResponse {
    List<MarsPhoto> photos = new ArrayList<>();

    @Override
    public String toString() {
        return "MarsRoverApiResponse{" +
                "photos=" + photos +
                '}';
    }
}
