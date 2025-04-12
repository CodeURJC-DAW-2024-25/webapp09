package es.grupo9.practica1.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import es.grupo9.practica1.DTOs.ReviewDTO;
import es.grupo9.practica1.entities.Housing;

import es.grupo9.practica1.entities.Review;
import es.grupo9.practica1.entities.User;
import es.grupo9.practica1.repository.HousingRepository;
import es.grupo9.practica1.repository.ReviewRepository;
import es.grupo9.practica1.repository.UserRepository;


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

    public void initializeComments() {

        Optional<User> defUser = userRepository.findByName("Pepe");
        Optional<Housing> defHousing = housingRepository.findById(1);
        List<Review> comments = Arrays.asList(

                new Review(reviewCounter++, 100, "Godin el sitio", defHousing.get(), defUser.get()),
                new Review(reviewCounter++, 50, "Normalito, la verdad que mejorable", defHousing.get(), defUser.get()),
                new Review(reviewCounter++, 25, "Llego a saber que no tienen baño y no reservo", defHousing.get(), defUser.get()));
        reviewRepository.saveAll(comments);
    }

    public List<ReviewDTO> getAllReviews(){
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewDTO> reviewDTOs = new ArrayList<>();

        for (Review review: reviews) {
            reviewDTOs.add(new ReviewDTO(review));
            
        }
        return reviewDTOs;
    }
    public ReviewDTO getReviewById(Integer id){
        Optional<Review> review = reviewRepository.findById(id);
        ReviewDTO reviewDTO = new ReviewDTO(review.get());
        return reviewDTO;

    }

    public ReviewDTO createReview(ReviewDTO review){
        Review newReview = new Review(review.getReviewId(),review.getRating(),review.getComment(),housingRepository.findByCode(review.getHotelCode()).get(),userRepository.findById(review.getUserDni()).get());
        reviewRepository.save(newReview);
        return new ReviewDTO(newReview);

    }

    public ReviewDTO updateReview(Integer id,ReviewDTO review){
        Optional<Review> originalReview = reviewRepository.findById(id);
        Review finalOriginalReview = originalReview.get();

        finalOriginalReview.setReviewId(review.getReviewId());
        finalOriginalReview.setComment(review.getComment());
        finalOriginalReview.setHotel(housingRepository.findByCode(review.getHotelCode()).get());
        finalOriginalReview.setRating(review.getRating());
        finalOriginalReview.setUser(userRepository.findById(review.getUserDni()).get());

        reviewRepository.save(finalOriginalReview);

        return new ReviewDTO(finalOriginalReview);
    }

    public void deleteReview(Integer id){
        reviewRepository.deleteById(id);
    }


    public Page<ReviewDTO> findByHotel(int code,  Pageable pageable){
        Housing house = housingRepository.findByCode(code).get();
        Page<Review> paginatedReview = reviewRepository.findByHotel(house, pageable);

        return paginatedReview.map(review -> {
            ReviewDTO dto = new ReviewDTO();
            dto.setReviewId(review.getReviewId());
            dto.setRating(review.getRating());
            dto.setComment(review.getComment());
            dto.setHotelCode(review.getHotel().getCode());
            dto.setUserDni(review.getUser().getDni());
            return dto;
        });



    }

    public List<ReviewDTO> getReviewsByHouse(Integer code){

        var allReviews = reviewRepository.findAll();
        allReviews.stream()
                    .filter(review -> review.getHotel().getCode() == code)// igual da error que cod ees int no Integer
                    .limit(3)
                    .collect(Collectors.toList());

        List<ReviewDTO> reviewDTOs = new ArrayList<>(); 
        for (Review review : allReviews) {
            reviewDTOs.add(new ReviewDTO(review));
        }

        return reviewDTOs;
    }
}
