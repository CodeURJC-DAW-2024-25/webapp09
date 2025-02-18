package es.grupo9.practica1;

import jakarta.persistence.*;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Reserva;

    private int ID_cliente;

    @ManyToOne
    @JoinColumn(name = "Cod_Hotel", referencedColumnName = "codigo", nullable = false)
    private Alojamiento alojamiento;

    public Reserva() {
    }

    public Reserva(int ID_Reserva, int ID_cliente, Alojamiento alojamiento) {
        this.ID_Reserva = ID_Reserva;
        this.ID_cliente = ID_cliente;
        this.alojamiento = alojamiento;
    }

    public int getID_Reserva() {
        return ID_Reserva;
    }

    public void setID_Reserva(int ID_Reserva) {
        this.ID_Reserva = ID_Reserva;
    }

    public int getID_cliente() {
        return ID_cliente;
    }

    public void setID_cliente(int ID_cliente) {
        this.ID_cliente = ID_cliente;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }
}
