package es.grupo9.practica1.DTOs;

import es.grupo9.practica1.entities.Review;

public class ReviewDTO {

    private Integer reviewId;
    private Integer rating;
    private String comment;
    private int hotelCode;
    private String userName;
    private String userDni;

    public ReviewDTO() {
    }

    public ReviewDTO(Integer rating, String comment, int hotelCode, String userDni, String userName) {
        this.rating = rating;
        this.comment = comment;
        this.hotelCode = hotelCode;
        this.userDni = userDni;
        this.userName = userName;
    }

    public ReviewDTO(Review review) {
        this.reviewId = review.getReviewId();
        this.rating = review.getRating();
        this.comment = review.getComment();
        this.hotelCode = review.getHotel().getCode();
        this.userName = review.getUser().getName();
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

    public int getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(int hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserDni() {
        return this.userDni;
    }

    public void setUserDni(String userDni) {
        this.userDni = userDni;
    }

}
