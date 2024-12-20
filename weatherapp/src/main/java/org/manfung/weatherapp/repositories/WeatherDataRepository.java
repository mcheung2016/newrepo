package org.manfung.weatherapp.repositories;

import org.manfung.weatherapp.models.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    WeatherData findByCity(String city);
}

