package com.marsRoverApi.service;

import com.marsRoverApi.entity.HomeEntity;
import com.marsRoverApi.repository.PreferencesRepository;
import com.marsRoverApi.response.MarsPhoto;
import com.marsRoverApi.response.MarsRoverApiResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.util.*;

import static java.util.Objects.*;

@Service
public class MarsRoverApiService {

    @Autowired
    private PreferencesRepository preferencesRepository;

    private final static String KEY = "I8Ga5duFgBhQp1dM0jTE3QujXqx1yrFpUNormdqm";
    @Getter private Map<String, List<String>> validCameras = new HashMap<>();

    public MarsRoverApiService() {
        validCameras.put("Opportunity", Arrays.asList("FHAZ", "RHAZ", "NAVCAM", "PANCAM", "MINITES"));
        validCameras.put("Curiosity", Arrays.asList("FHAZ", "RHAZ", "MAST", "CHEMCAM", "MAHLI", "MARDI", "NAVCAM"));
        validCameras.put("Spirit", Arrays.asList("FHAZ", "RHAZ", "NAVCAM", "PANCAM", "MINITES"));
    }

    public MarsRoverApiResponse getRoverData(HomeEntity homeEntity) {
        RestTemplate rt = new RestTemplate();

        List<String> apiUrlEndpoints = getApiUrlEndpoints(homeEntity);
        List<MarsPhoto> photos = new ArrayList<>();
        MarsRoverApiResponse response = new MarsRoverApiResponse();

        apiUrlEndpoints.forEach(url -> {
            MarsRoverApiResponse apiResponse = rt.getForObject(url, MarsRoverApiResponse.class);
            photos.addAll(requireNonNull(apiResponse).getPhotos());
        });

        response.setPhotos(photos);
        return response;
    }

    private List<String> getApiUrlEndpoints(HomeEntity homeEntity) {
        List<String> urls = new ArrayList<>();

        Method[] methods = homeEntity.getClass().getMethods();

        for (Method method : methods) {
            if (method.getName().contains("getCamera")) {
                String camName = method.getName().split("getCamera")[1].toUpperCase();
                if (validCameras.get(homeEntity.getMarsRoverName()).contains(camName)) {
                    urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/" + homeEntity.getMarsRoverName()
                            + "/photos?sol=" + homeEntity.getMarsSol() + "&api_key=" + KEY + "&camers=" + camName);
                }
            }
        }
        return urls;
    }

    public HomeEntity save(HomeEntity homeEntity) {
        return preferencesRepository.save(homeEntity);
    }

    public HomeEntity findByUserId(Long userId) {
        return preferencesRepository.findByUserId(userId);
    }
}
