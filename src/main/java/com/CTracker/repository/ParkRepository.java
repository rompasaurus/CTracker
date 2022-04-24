package com.CTracker.repository;

import com.CTracker.model.Park;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;


public interface ParkRepository extends JpaRepository<Park, Long> {
    Optional<Park> findById(Long id);

    @Query(value = "SELECT p.country FROM park p group by p.country ORDER BY p.country", nativeQuery = true)
    Collection<String> findAllCountries();

    @Query(value = "SELECT p.state FROM park p WHERE p.country = :country group by p.state ORDER BY p.state", nativeQuery = true)
    Collection<String> findAllStatesByCountry(@Param("country") String country);

    @Query(value = "SELECT distinct p.city FROM park p WHERE p.state = :state ORDER BY p.city", nativeQuery = true)
    Collection<String> findAllCitiesByState(@Param("state") String state);

    @Query(value = "SELECT * FROM park p WHERE p.country = :country order by p.park_name", nativeQuery = true)
    Collection<Park> findAllParksByCountry(@Param("country") String country);

    @Query(value = "SELECT * FROM park p WHERE p.city = :city", nativeQuery = true)
    Collection<Park> findAllParksByCity(@Param("city") String city);

    @Query(value = "SELECT * FROM park p WHERE p.state = :state", nativeQuery = true)
    Collection<Park> findAllParksByState(@Param("state") String state);

    @Query(value = "SELECT distinct p.city FROM park p", nativeQuery = true)
    Collection<String> findAllCities();

    @Query(value = "SELECT distinct p.state FROM park p", nativeQuery = true)
    Collection<String> findAllStates();
}

