package es.grupo9.practica1.DTOs;

import es.grupo9.practica1.entities.Review;

public class ReviewDTO {

    private Integer reviewId;
    private Integer rating;
    private String comment;
    private Integer hotelCode;
    private String userDni;

    public ReviewDTO() {
    }

    public ReviewDTO(Integer reviewId, Integer rating, String comment, Integer hotelCode, String userDni) {
        this.reviewId = reviewId;
        this.rating = rating;
        this.comment = comment;
        this.hotelCode = hotelCode;
        this.userDni = userDni;
    }

    public ReviewDTO(Review review) {
        this.reviewId = review.getReviewId();
        this.rating = review.getRating();
        this.comment = review.getComment();
        this.hotelCode = review.getHotel().getCode();
        this.userDni = review.getUser().getDni();
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(Integer hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getUserDni() {
        return userDni;
    }

    public void setUserDni(String userDni) {
        this.userDni = userDni;
    }
}
