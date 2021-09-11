package com.marsRoverApi.repository;

import com.marsRoverApi.entity.HomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferencesRepository extends JpaRepository<HomeEntity, Long> {

    //how it can query can be made
//    @Query("select dto from HomeDto dto where userId = :userId")
//    @Query(value = "select * from mars_api_preferences where user_id = :userId", nativeQuery = true)
    HomeEntity findByUserId(Long userId);
}
