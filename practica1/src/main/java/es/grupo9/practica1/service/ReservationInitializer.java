package es.grupo9.practica1.service;

import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import es.grupo9.practica1.entities.Client;
import es.grupo9.practica1.entities.Housing;
import es.grupo9.practica1.entities.Reservation;
import es.grupo9.practica1.entities.Review;
import es.grupo9.practica1.entities.User;
import es.grupo9.practica1.repository.HousingRepository;
import es.grupo9.practica1.repository.ReservationRepository;
import es.grupo9.practica1.repository.ReviewRepository;
import es.grupo9.practica1.repository.UserRepository;

@Component
public class ReservationInitializer implements ApplicationRunner {
    
    private int reservationCounter = 1;
    private int reviewCounter = 1;

    @Autowired
    private HousingRepository housingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        // This runs AFTER application is fully ready
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


        List<Review> comments = Arrays.asList(

                new Review(reviewCounter++, 100, "Godin el sitio", defHousing.get(), defUser.get()),
                new Review(reviewCounter++, 50, "Normalito, la verdad que mejorable", defHousing.get(), defUser.get()),
                new Review(reviewCounter++, 25, "Llego a saber que no tienen baño y no reservo", defHousing.get(), defUser.get()));
        reviewRepository.saveAll(comments);

    
    }
}