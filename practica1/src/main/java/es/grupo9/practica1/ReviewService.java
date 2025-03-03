package es.grupo9.practica1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    
    public Review addReview(){
        Review newReview = new Review();

        return reviewRepository.save(newReview);

    }


    
}
