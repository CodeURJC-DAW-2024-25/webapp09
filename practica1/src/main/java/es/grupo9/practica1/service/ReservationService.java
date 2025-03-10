package es.grupo9.practica1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.grupo9.practica1.entities.Client;
import es.grupo9.practica1.entities.Housing;
import es.grupo9.practica1.entities.Reservation;
import es.grupo9.practica1.entities.User;
import es.grupo9.practica1.repository.HousingRepository;
import es.grupo9.practica1.repository.ReservationRepository;
import es.grupo9.practica1.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Calendar;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HousingRepository housingRepository;

    private int reservationCounter = 1;

    @PostConstruct
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

    public Reservation addReservation(  User client, Housing housing, Date check_in, Date check_out) {
        

        

        // Create new hotel and save
        Reservation newReservation = new Reservation( reservationCounter, client, housing, check_in, check_out);
        return reservationRepository.save(newReservation);
    }

}
