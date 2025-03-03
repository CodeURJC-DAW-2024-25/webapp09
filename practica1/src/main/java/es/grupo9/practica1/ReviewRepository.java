package es.grupo9.practica1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query("SELECT MAX(review_ID) FROM Review")
    int maxReviewId();
    List<Review> findByHotel(Housing hotel);
    List<Review> findByUser(User user);
}