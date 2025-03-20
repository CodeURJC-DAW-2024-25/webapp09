package es.grupo9.practica1.DTOs;

public class ReviewDTO {
    private Integer reviewId;
    private Integer rating;
    private String comment;
    private Integer hotelCode; // Solo necesitamos el código del hotel
    private String userId; // Solo necesitamos el ID del usuario

    // Getters y Setters
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}