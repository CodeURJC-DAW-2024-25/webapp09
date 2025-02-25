package es.grupo9.practica1;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservation_ID;

    @ManyToOne
    @JoinColumn(name = "Client_ID", referencedColumnName = "dni", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "Hotel_Code", referencedColumnName = "code", nullable = false)
    private Housing housing;

    public Reservation(){}

    public Reservation(int reservation_ID, Client client, Housing housing) {
        this.reservation_ID = reservation_ID;
        this.client = client;
        this.housing = housing;
    }

    public int getReservation_ID() {
        return reservation_ID;
    }

    public void setReservation_ID(int reservation_ID) {
        this.reservation_ID = reservation_ID;
    }

    public Client getID_cliente() {
        return this.client;
    }

    public void setID_cliente(Client Client_ID) {
        this.client = Client_ID;
    }

    public Housing getHousing() {
        return housing;
    }

    public void setHousing(Housing housing) {
        this.housing = housing;
    }
}
