package es.grupo9.practica1.controllers;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.grupo9.practica1.DTOs.ReservationDTO;
import es.grupo9.practica1.entities.Reservation;
import es.grupo9.practica1.entities.User;
import es.grupo9.practica1.entities.Housing;
import es.grupo9.practica1.repositories.ReservationRepository;
import es.grupo9.practica1.repositories.UserRepository;
import es.grupo9.practica1.repositories.HousingRepository;

@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HousingRepository housingRepository;

    @GetMapping
    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .map(ReservationDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Integer id) {
        return reservationRepository.findById(id)
                .map(ReservationDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/client/{dni}")
    public List<ReservationDTO> getReservationsByClient(@PathVariable String dni) {
        return reservationRepository.findByClientDni(dni).stream()
                .map(ReservationDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/housing/{code}")
    public List<ReservationDTO> getReservationsByHousing(@PathVariable String code) {
        return reservationRepository.findByHousingCode(code).stream()
                .map(ReservationDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        User client = userRepository.findById(reservationDTO.getClientDni())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        Housing housing = housingRepository.findById(reservationDTO.getHousingCode())
                .orElseThrow(() -> new RuntimeException("Housing not found"));

        Reservation reservation = new Reservation();
        reservation.setCheck_in(reservationDTO.getCheckIn());
        reservation.setCheck_out(reservationDTO.getCheckOut());
        reservation.setValorated(reservationDTO.isValorated());
        reservation.setID_cliente(client);
        reservation.setHousing(housing);

        Reservation savedReservation = reservationRepository.save(reservation);
        return ResponseEntity.ok(new ReservationDTO(savedReservation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(
            @PathVariable Integer id,
            @RequestBody ReservationDTO reservationDTO) {
        return reservationRepository.findById(id)
                .map(reservation -> {
                    reservation.setCheck_in(reservationDTO.getCheckIn());
                    reservation.setCheck_out(reservationDTO.getCheckOut());
                    reservation.setValorated(reservationDTO.isValorated());
                    
                    if (!reservation.getID_cliente().getDni().equals(reservationDTO.getClientDni())) {
                        User client = userRepository.findById(reservationDTO.getClientDni())
                                .orElseThrow(() -> new RuntimeException("Client not found"));
                        reservation.setID_cliente(client);
                    }
                    
                    if (!reservation.getHousing().getCode().equals(reservationDTO.getHousingCode())) {
                        Housing housing = housingRepository.findById(reservationDTO.getHousingCode())
                                .orElseThrow(() -> new RuntimeException("Housing not found"));
                        reservation.setHousing(housing);
                    }
                    
                    Reservation updatedReservation = reservationRepository.save(reservation);
                    return ResponseEntity.ok(new ReservationDTO(updatedReservation));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Integer id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/valorate")
    public ResponseEntity<ReservationDTO> markAsValorated(@PathVariable Integer id) {
        return reservationRepository.findById(id)
                .map(reservation -> {
                    reservation.setValorated(true);
                    Reservation updatedReservation = reservationRepository.save(reservation);
                    return ResponseEntity.ok(new ReservationDTO(updatedReservation));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/availability")
    public ResponseEntity<Boolean> checkAvailability(
            @RequestParam String housingCode,
            @RequestParam Date checkIn,
            @RequestParam Date checkOut) {
        boolean isAvailable = reservationRepository
                .findByHousingCodeAndCheckInLessThanEqualAndCheckOutGreaterThanEqual(housingCode, checkOut, checkIn)
                .isEmpty();
        return ResponseEntity.ok(isAvailable);
    }
}