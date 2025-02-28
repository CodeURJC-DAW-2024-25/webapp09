package es.grupo9.practica1;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    int maxReviewId();
    List<Review> findByHotel(Housing hotel);
    List<Review> findByUser(User user);
}