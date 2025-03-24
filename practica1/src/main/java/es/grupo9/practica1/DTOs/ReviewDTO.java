package es.grupo9.practica1.DTOs;

import es.grupo9.practica1.entities.Review;

public final class ReviewDTO {

    private Integer reviewId;
    private Integer rating;
    private String comment;
    private int hotelCode;
    private String userDni;

    /* Constructores */
    public ReviewDTO() {}

    public ReviewDTO(
        final Integer reviewId, 
        final Integer rating, 
        final String comment, 
        final int hotelCode, 
        final String userDni
    ) {
        this.reviewId = reviewId;
        this.rating = rating;
        this.comment = comment;
        this.hotelCode = hotelCode;
        this.userDni = userDni;
    }

    public ReviewDTO(final Review review) {
        this(
            review.getReviewId(),
            review.getRating(),
            review.getComment(),
            review.getHotel().getCode(),
            review.getUser().getDni()
        );
    }

    /* Accessors */
    public Integer getReviewId() { 
        return this.reviewId; 
    }

    public void setReviewId(final Integer reviewId) { 
        this.reviewId = reviewId; 
    }

    public Integer getRating() { 
        return this.rating; 
    }

    public void setRating(final Integer rating) { 
        this.rating = rating; 
    }

    public String getComment() { 
        return this.comment; 
    }

    public void setComment(final String comment) { 
        this.comment = comment; 
    }

    public int getHotelCode() { 
        return this.hotelCode; 
    }

    public void setHotelCode(final int hotelCode) { 
        this.hotelCode = hotelCode; 
    }

    public String getUserDni() { 
        return this.userDni; 
    }

    public void setUserDni(final String userDni) { 
        this.userDni = userDni; 
    }
}