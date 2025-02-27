package es.grupo9.practica1;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
   // @Query("SELECT Hotel_Name name, check_in, check_out FROM Reservation r WHERE r.client.dni = ?1")
    //List<Reservation> getReservationsByClient(String dni);
}