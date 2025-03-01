package es.grupo9.practica1;

import java.sql.Date;

import jakarta.persistence.Column;
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

    @Column(name = "check_in", nullable = false, unique = false, length = 9)
    private Date check_in;

    @Column(name = "check_out", nullable = false, unique = false, length = 9)
    private Date check_out;

    @Column(name="valorated", unique = false, length = 1)
    private boolean valorated;

    @ManyToOne
    @JoinColumn(name = "Client_ID", referencedColumnName = "dni", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "Hotel_Code", referencedColumnName = "code", nullable = false)
    private Housing housing;

    @ManyToOne
    @JoinColumn(name = "Hotel_Name", referencedColumnName = "name", nullable = false)
    private Housing housing_name;

    
    public Reservation(){}

    public Reservation(int reservation_ID, Client client, Housing housing, Date check_in, Date check_out) {
        this.reservation_ID = reservation_ID;
        this.client = client;
        this.housing = housing;
        this.check_in = check_in;
        this.check_out = check_out;
        this.valorated = false;
        this.housing_name = housing; //esto hay que cambiarlo
    }

    public int getReservation_ID() {
        return reservation_ID;
    }

    public void setReservation_ID(int reservation_ID) {
        this.reservation_ID = reservation_ID;
    }

    public boolean isValorated() {
        return valorated;
    }

    public void setValorated(boolean valorated) {
        this.valorated = valorated;
    }

    public Date getCheck_in() {
        return check_in;
    }

    public void setCheck_in(Date check_in) {
        this.check_in = check_in;
    }

    public Date getCheck_out() {
        return check_out;
    }

    public void setCheck_out(Date check_out) {
        this.check_out = check_out;
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
