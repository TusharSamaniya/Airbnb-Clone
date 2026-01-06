package com.tushar.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tushar.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	@Query("SELECT b FROM Booking b WHERE b.property.id = :propertyId " +
	           "AND (b.checkIn < :checkOut AND b.checkOut > :checkIn)")
	List<Booking> findOverlappingBookings(Long propertyId, LocalDate checkIn, LocalDate checkOut);

}
