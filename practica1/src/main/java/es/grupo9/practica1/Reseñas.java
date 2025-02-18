package es.grupo9.practica1;


import javax.persistence.*;
import java.util.List;



@Entity


public class Reseña {

    public Reseña(int estrellas, String comentarios, String idReserva, String idHotel, String idUsuario) {
        this.estrellas = estrellas;
        this.comentarios = comentarios;
        this.idReserva = idReserva;
        this.idHotel = idHotel;
        this.idUsuario = idUsuario;
    }

        // Getters y Setters
        public Long getIdReservas() {
            return idReservas;
        }
    
        public void setIdReservas(Long idReservas) {
            this.idReservas = idReservas;
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
    
        public String getIdReserva() {
            return idReserva;
        }
    
        public void setIdReserva(String idReserva) {
            this.idReserva = idReserva;
        }
    
        public String getIdHotel() {
            return idHotel;
        }
    
        public void setIdHotel(String idHotel) {
            this.idHotel = idHotel;
        }
    
        public String getIdUsuario() {
            return idUsuario;
        }
    
        public void setIdUsuario(String idUsuario) {
            this.idUsuario = idUsuario;
        }

    @Id
    @Column(name = "id_reservas")
    private Long idReservas;

    @Column(name = "estrellas", nullable = false)
    private int estrellas;

    @Column(name = "comentarios")
    private String comentarios;

    @Column(name = "ID_reserva", nullable = false)
    private String idReserva;

    @Column(name = "ID_hotel", nullable = false)
    private String idHotel;

    @Column(name = "ID_usuario", nullable = false)
    private String idUsuario;
}