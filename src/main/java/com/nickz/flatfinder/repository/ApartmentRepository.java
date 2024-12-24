package com.nickz.flatfinder.repository;

import com.nickz.flatfinder.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}
