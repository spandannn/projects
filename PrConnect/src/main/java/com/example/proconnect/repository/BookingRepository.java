package com.example.proconnect.repository;

import com.example.proconnect.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByProfessionalId(Long professionalId);
    List<Booking> findByClientId(Long clientId);
}
