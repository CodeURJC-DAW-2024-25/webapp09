package es.grupo9.practica1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    private Integer reviewCounter = 1;
    
    public Review addReview(Integer rating, String comment, Housing hotel, User user){
        Review newReview = new Review(reviewCounter++, rating , comment,hotel,user);

        return reviewRepository.save(newReview);

    }


    
}
