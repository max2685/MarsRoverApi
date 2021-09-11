package com.marsRoverApi.controller;

import com.marsRoverApi.entity.HomeEntity;
import com.marsRoverApi.response.MarsRoverApiResponse;
import com.marsRoverApi.service.MarsRoverApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    private MarsRoverApiService roverApiService;

    @GetMapping("/")
    public String getHomeView(ModelMap model, Long userId, Boolean createUser) {
        HomeEntity homeEntity = createDefault(userId);

        if (Boolean.TRUE.equals(createUser) && userId == null) {
            homeEntity = roverApiService.save(homeEntity);
        } else {
            homeEntity = roverApiService.findByUserId(userId);
            if (homeEntity == null) {
                homeEntity = createDefault(userId);
            }
        }

        MarsRoverApiResponse roverData = roverApiService.getRoverData(homeEntity);
        model.put("roverData", roverData);
        model.put("homeEntity", homeEntity);
        model.put("validCameras", roverApiService.getValidCameras().get(homeEntity.getMarsRoverName()));

        if(!Boolean.TRUE.equals(homeEntity.getRememberPreferences()) && userId != null) {
            HomeEntity defaultHomeEntity = createDefault(userId);
            roverApiService.save(defaultHomeEntity);
        }
        return "index";
    }

    @GetMapping("/savedPreferences")
    @ResponseBody
    public HomeEntity getSavedPreferences(Long userId) {
        if (userId != null) return roverApiService.findByUserId(userId);
        else return createDefault(userId);
    }

    @PostMapping("/")
    public String postHomeView(HomeEntity homeEntity) {
        homeEntity = roverApiService.save(homeEntity);
        return "redirect:/?userId=" + homeEntity.getUserId();
    }

    private HomeEntity createDefault(Long userId) {
        HomeEntity homeEntity = new HomeEntity();
        homeEntity.setMarsRoverName("Opportunity");
        homeEntity.setMarsSol(1);
        homeEntity.setUserId(userId);
        return homeEntity;
    }
}
