package com.marsRoverApi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MarsCamera {
    private Long id;
    private String name;
    @JsonProperty("rover_id")
    private Long roverId;
    @JsonProperty("full_name")
    private String fullName;

    @Override
    public String toString() {
        return "MarsCamera{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roverId=" + roverId +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
