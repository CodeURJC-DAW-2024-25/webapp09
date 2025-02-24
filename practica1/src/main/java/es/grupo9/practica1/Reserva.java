package es.grupo9.practica1;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Reserva;

    @ManyToOne
    @JoinColumn(name = "ID_Cliente", referencedColumnName = "dni", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "Cod_Hotel", referencedColumnName = "codigo", nullable = false)
    private Alojamiento alojamiento;

    public Reserva(){}

    public Reserva(int ID_Reserva, Cliente cliente, Alojamiento alojamiento) {
        this.ID_Reserva = ID_Reserva;
        this.cliente = cliente;
        this.alojamiento = alojamiento;
    }

    public int getID_Reserva() {
        return ID_Reserva;
    }

    public void setID_Reserva(int ID_Reserva) {
        this.ID_Reserva = ID_Reserva;
    }

    public Cliente getID_cliente() {
        return this.cliente;
    }

    public void setID_cliente(Cliente ID_cliente) {
        this.cliente = ID_cliente;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }
}
