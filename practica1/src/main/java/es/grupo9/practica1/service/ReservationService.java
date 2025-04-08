package es.grupo9.practica1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import es.grupo9.practica1.DTOs.ReservationDTO;
import es.grupo9.practica1.entities.Client;
import es.grupo9.practica1.entities.Housing;
import es.grupo9.practica1.entities.Reservation;
import es.grupo9.practica1.entities.User;
import es.grupo9.practica1.repository.HousingRepository;
import es.grupo9.practica1.repository.ReservationRepository;
import es.grupo9.practica1.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Calendar;


@Service
public class ReservationService {
    
    @Autowired
    private ReservationInitializer reservationInitializer;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HousingRepository housingRepository;

    private int reservationCounter = 1;


    
    public void initializeReservations() {
        Optional<User> defUser = userRepository.findByName("Pepe");
        Optional<Housing> defHousing = housingRepository.findById(1);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 10); // Check-in in 10 days
        Date checkIn1 = new Date(calendar.getTimeInMillis());

        calendar.add(Calendar.DAY_OF_MONTH, 5); // Check-out 5 days after check-in
        Date checkOut1 = new Date(calendar.getTimeInMillis());

        // List of users and admin
        List<Reservation> prechargedReservations = Arrays.asList(

                new Reservation(reservationCounter++, (Client) defUser.get(), defHousing.get(), checkIn1, checkOut1),
                new Reservation(reservationCounter++, (Client) defUser.get(), defHousing.get(), checkIn1, checkOut1)

        );

        reservationRepository.saveAll(prechargedReservations);

    }




    public List<ReservationDTO> getAllReservations(){
        List<Reservation> reservationList = reservationRepository.findAll();
        List<ReservationDTO> reservationDTOList = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            reservationDTOList.add(new ReservationDTO(reservation));
        }
        return reservationDTOList;

    }

    public ReservationDTO getReservationById(Integer id){
        Optional<Reservation> reservation = reservationRepository.findById(id);
        ReservationDTO reservationDTO = new ReservationDTO(reservation.get());
        return reservationDTO;

    }

    public ReservationDTO createReservation(ReservationDTO reservation){
        Reservation newReservation = new Reservation(reservationCounter++, userRepository.findById(reservation.getClientDni()).get(),housingRepository.findByCode(reservation.getHousingCode()).get(),reservation.getCheckIn(),
        reservation.getCheckOut(),reservation.isValorated());
        reservationRepository.save(newReservation);
        return new ReservationDTO(newReservation);

    }
    public ReservationDTO updateReservation(Integer id, ReservationDTO reservation){
        Optional<Reservation> originalReservation = reservationRepository.findById(id);
        Reservation finalOriginalReservation = originalReservation.get();

        finalOriginalReservation.setCheck_in(reservation.getCheckIn());
        finalOriginalReservation.setCheck_out(reservation.getCheckOut());
        finalOriginalReservation.setHousing(housingRepository.findByCode(reservation.getHousingCode()).get());
        finalOriginalReservation.setID_cliente(userRepository.findById(reservation.getClientDni()).get());
        finalOriginalReservation.setId(id);
        finalOriginalReservation.setValorated(reservation.isValorated());

        reservationRepository.save(finalOriginalReservation);
        return new ReservationDTO(finalOriginalReservation);
    }

    public void deleteReservation(Integer id){
        reservationRepository.deleteById(id);

        
    }

    public void acceptReservation(Integer id){

        Reservation reservation = reservationRepository.findById(id).get();
        reservation.setValorated(true);
        reservationRepository.save(reservation);
    }



    public Page<ReservationDTO> findByValoratedFalse(Pageable pageable){
        Page<Reservation> reservations = reservationRepository.findByValoratedFalse(pageable);


        return reservations.map(reserves -> {
        ReservationDTO dto = new ReservationDTO();

        dto.setCheckIn(reserves.getCheck_in());
        dto.setCheckOut(reserves.getCheck_out());
        dto.setClientDni(reserves.getID_cliente().getDni());
        dto.setHousingCode(reserves.getHousing().getCode());
        dto.setHousingName(reserves.getHousing().getName());
        dto.setValorated(false);
        dto.setId(reserves.getId());

        return dto;
        }
        
        );
        
    }
}


