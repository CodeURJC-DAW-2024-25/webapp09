package es.grupo9.practica1;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.grupo9.practica1.entities.Housing;
import es.grupo9.practica1.entities.Review;
import es.grupo9.practica1.entities.User;
import es.grupo9.practica1.repository.HousingRepository;
import es.grupo9.practica1.repository.UserRepository;
import jakarta.annotation.PostConstruct;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HousingRepository housingRepository;



    private Integer reviewCounter = 1;

    public Review addReview(Integer rating, String comment, Housing hotel, User user) {
        Review newReview = new Review(reviewCounter++, rating, comment, hotel, user);

        return reviewRepository.save(newReview);

    }

    @PostConstruct
    public void initializeComments() {

        Optional<User> defUser = userRepository.findByName("Pepe");
        Optional<Housing> defHousing = housingRepository.findById(1);
        List<Review> comments = Arrays.asList(

                new Review(reviewCounter++, 100, "Godin el sitio", defHousing.get(), defUser.get()),
                new Review(reviewCounter++, 50, "Normalito, la verdad que mejorable", defHousing.get(), defUser.get()),
                new Review(reviewCounter++, 25, "Llego a saber que no tienen baño y no reservo", defHousing.get(), defUser.get()));
        reviewRepository.saveAll(comments);
    }

    
}
