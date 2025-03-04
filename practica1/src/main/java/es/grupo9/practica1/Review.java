package es.grupo9.practica1;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Review {
    public Review(){}

    public Review(Integer review_ID, int rating, String comment, Reservation reservation, Housing hotel, User user) {
        this.review_ID = review_ID;
        this.rating = rating;
        this.comment = comment;
        this.reservation = reservation;
        this.hotel = hotel;
        this.user = user;
    }

    public Integer getReview_ID() {
        return review_ID;
    }

    public void setReview_ID(Integer review_ID) {
        this.review_ID = review_ID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReserva(Reservation reservation) {
        this.reservation = reservation;
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
    @Column(name = "review_ID")
    private Integer review_ID;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "comment", nullable = false)
    private String comment;

    @OneToOne
    @JoinColumn(name = "reservation_ID", referencedColumnName = "id", nullable = false)
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "Hotel_code", referencedColumnName = "code",nullable = false)
    private Housing hotel;

    @ManyToOne
    @JoinColumn(name = "user_ID", referencedColumnName = "dni", nullable = false)

    private User user;

}