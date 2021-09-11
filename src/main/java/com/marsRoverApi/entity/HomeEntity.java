package com.marsRoverApi.entity;

import lombok.Data;

import javax.persistence.*;

//tells hibernate that this is a database table
@Entity
@Table(name = "mars_api_preferences")
@Data
public class HomeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(length = 20)
    private String marsRoverName;
    private Integer marsSol;
    private Boolean cameraFhaz;
    private Boolean cameraRhaz;
    private Boolean cameraMast;
    private Boolean cameraChemcam;
    private Boolean cameraMahli;
    private Boolean cameraMardi;
    private Boolean cameraNavcam;
    private Boolean cameraPancam;
    private Boolean cameraMinites;
    private Boolean rememberPreferences;
}