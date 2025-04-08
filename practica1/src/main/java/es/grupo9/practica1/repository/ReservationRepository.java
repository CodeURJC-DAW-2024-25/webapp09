package es.grupo9.practica1.repository;

import org.springframework.stereotype.Repository;

import es.grupo9.practica1.entities.Housing;
import es.grupo9.practica1.entities.Reservation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



@Repository

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    
    Page<Reservation> findByValoratedFalse(Pageable pageable);
}