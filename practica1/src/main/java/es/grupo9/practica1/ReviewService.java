package es.grupo9.practica1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private HousingRepository housingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;
    
    // Método para agregar una nueva revisión
    public Review addReview(int stars, String comment, Reservation reservation, Housing hotel, User user) {
        // Obtener el ID más alto actual
        int reviewId = reviewRepository.maxReviewId();
        if (reviewId == 0) {
            reviewId = 1; // Si no hay revisiones, iniciar desde 1
        } else {
            reviewId += 1;
        }

        // Crear la nueva revisión y guardarla
        Review newReview = new Review(reviewId, stars, comment, reservation, hotel, user);
        return reviewRepository.save(newReview);
    }

    // Método para obtener todas las revisiones
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // Método para obtener una revisión por su ID
    public Review getReviewById(int reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    // Método para actualizar una revisión
    public Review updateReview(int reviewId, int stars, String comment) {
        Review existingReview = reviewRepository.findById(reviewId).orElse(null);
        if (existingReview != null) {
            existingReview.setStars(stars);
            existingReview.setComment(comment);
            return reviewRepository.save(existingReview);
        }
        return null;
    }

    // Método para eliminar una revisión por su ID
    public void deleteReview(int reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    // Método para obtener todas las revisiones de un hotel específico
    public List<Review> getReviewsByHotel(Housing hotel) {
        return reviewRepository.findByHotel(hotel);
    }

    // Método para obtener todas las revisiones de un usuario específico
    public List<Review> getReviewsByUser(User user) {
        return reviewRepository.findByUser(user);
    }
}
