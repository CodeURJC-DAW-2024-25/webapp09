package es.grupo9.practica1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
    public Review(){}

    public Review(Integer reviewId, Integer rating, String comment, Housing hotel, User user) {
        this.reviewId = reviewId;
        this.rating = rating;
        this.comment = comment;
        this.hotel = hotel;
        this.user = user;
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



    public Housing getHotel() {
        return hotel;
    }

    public void setHotel(Housing hotel) {
        this.hotel = hotel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewId")
    private Integer reviewId;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "comment", nullable = false)
    private String comment;


    @ManyToOne
    @JoinColumn(name = "Hotel_code", referencedColumnName = "code",nullable = false)
    private Housing hotel;

    @ManyToOne
    @JoinColumn(name = "user_ID", referencedColumnName = "dni", nullable = false)

    private User user;

}