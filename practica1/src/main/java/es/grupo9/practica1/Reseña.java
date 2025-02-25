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
public class Reseña {
    public Reseña(){}

    public Reseña(int idResenia, int estrellas, String comentarios, Reserva reserva, Alojamiento hotel, Usuario usuario) {
        this.idResenia = idResenia;
        this.estrellas = estrellas;
        this.comentarios = comentarios;
        this.reserva = reserva;
        this.hotel = hotel;
        this.usuario = usuario;
    }

    public int getIdResenia() {
        return idResenia;
    }

    public void setIdResenia(int idResenia) {
        this.idResenia = idResenia;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Alojamiento getHotel() {
        return hotel;
    }

    public void setHotel(Alojamiento hotel) {
        this.hotel = hotel;
    }

    public Usuario getUsuarios() {
        return usuario;
    }

    public void setUsuarios(Usuario usuario) {
        this.usuario = usuario;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_resenia")
    private int idResenia;

    @Column(name = "estrellas", nullable = false)
    private int estrellas;

    @Column(name = "comentarios")
    private String comentarios;

    @OneToOne
    @JoinColumn(name = "ID_reserva", referencedColumnName = "ID_Reserva", nullable = false)
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "COD_hotel", referencedColumnName = "codigo",nullable = false)
    private Alojamiento hotel;

    @ManyToOne
    @JoinColumn(name = "ID_usuario", referencedColumnName = "dni", nullable = false)

    private Usuario usuario;

}