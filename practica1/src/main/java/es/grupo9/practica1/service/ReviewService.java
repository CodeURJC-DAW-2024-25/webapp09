package es.grupo9.practica1.service;

import java.util.Arrays; // Importación añadida
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.grupo9.practica1.DTOs.ReviewDTO;
import es.grupo9.practica1.entities.Housing;
import es.grupo9.practica1.entities.Review;
import es.grupo9.practica1.entities.User;
import es.grupo9.practica1.repository.HousingRepository;
import es.grupo9.practica1.repository.ReviewRepository;
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

    // Obtener todas las revisiones
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener una revisión por ID
    public ReviewDTO getReviewById(Integer id) {
        Optional<Review> review = reviewRepository.findById(id);
        return review.map(this::convertToDTO).orElse(null);
    }

    // Crear una nueva revisión
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Optional<User> user = userRepository.findById(reviewDTO.getUserId());
        Optional<Housing> housing = housingRepository.findById(reviewDTO.getHotelCode());

        if (user.isPresent() && housing.isPresent()) {
            Review review = new Review();
            review.setRating(reviewDTO.getRating());
            review.setComment(reviewDTO.getComment());
            review.setUser(user.get());
            review.setHotel(housing.get());

            Review savedReview = reviewRepository.save(review);
            return convertToDTO(savedReview);
        } else {
            throw new RuntimeException("User or Housing not found");
        }
    }

    // Actualizar una revisión existente
    public ReviewDTO updateReview(Integer id, ReviewDTO reviewDTO) {
        Optional<Review> existingReview = reviewRepository.findById(id);
        if (existingReview.isPresent()) {
            Review review = existingReview.get();
            review.setRating(reviewDTO.getRating());
            review.setComment(reviewDTO.getComment());

            Optional<User> user = userRepository.findById(reviewDTO.getUserId());
            Optional<Housing> housing = housingRepository.findById(reviewDTO.getHotelCode());

            if (user.isPresent() && housing.isPresent()) {
                review.setUser(user.get());
                review.setHotel(housing.get());
            } else {
                throw new RuntimeException("User or Housing not found");
            }

            Review updatedReview = reviewRepository.save(review);
            return convertToDTO(updatedReview);
        } else {
            throw new RuntimeException("Review not found");
        }
    }

    // Eliminar una revisión por ID
    public void deleteReview(Integer id) {
        reviewRepository.deleteById(id);
    }

    // Convertir entidad Review a ReviewDTO
    private ReviewDTO convertToDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setReviewId(review.getReviewId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setHotelCode(review.getHotel().getCode());
        dto.setUserId(review.getUser().getDni());
        return dto;
    }

    // Método para agregar una revisión (ya existente)
    public Review addReview(Integer rating, String comment, Housing hotel, User user) {
        Review newReview = new Review(reviewCounter++, rating, comment, hotel, user);
        return reviewRepository.save(newReview);
    }

    // Inicialización de datos (ya existente)
    @PostConstruct
    public void initializeComments() {
        Optional<User> defUser = userRepository.findByName("Pepe");
        Optional<Housing> defHousing = housingRepository.findById(1);
        if (defUser.isPresent() && defHousing.isPresent()) {
            List<Review> comments = Arrays.asList(
                    new Review(reviewCounter++, 100, "Godin el sitio", defHousing.get(), defUser.get()),
                    new Review(reviewCounter++, 50, "Normalito, la verdad que mejorable", defHousing.get(), defUser.get()),
                    new Review(reviewCounter++, 25, "Llego a saber que no tienen baño y no reservo", defHousing.get(), defUser.get()));
            reviewRepository.saveAll(comments);
        }
    }
}