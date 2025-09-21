package com.furniture.store.repository;

import com.furniture.store.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
    List<City> findByNameContaining(String name);
}
