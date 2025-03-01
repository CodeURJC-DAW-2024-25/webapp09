package es.grupo9.practica1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
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

        calendar.add(Calendar.DAY_OF_MONTH, 5);  // Check-out 5 days after check-in
        Date checkOut1 = new Date(calendar.getTimeInMillis());

        // Lista de usuarios administradores
        List<Reservation> prechargedReservations = Arrays.asList(

        new Reservation(reservationCounter++,(Client) defUser.get(), defHousing.get(), checkIn1, checkOut1),
        new Reservation(reservationCounter++,(Client) defUser.get(), defHousing.get(), checkIn1, checkOut1)

        );

        reservationRepository.saveAll(prechargedReservations);
    
    }


}
