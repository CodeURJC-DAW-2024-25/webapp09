package es.grupo9.practica1.entities;

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
    private Integer id;

    @Column(name = "check_in", nullable = false, unique = false, length = 9)
    private Date check_in;

    @Column(name = "check_out", nullable = false, unique = false, length = 9)
    private Date check_out;

    @Column(name="valorated", unique = false, length = 1)
    private boolean valorated;

    @ManyToOne
    @JoinColumn(name = "Client_ID", referencedColumnName = "dni", nullable = false)
    private User client;

    @ManyToOne
    @JoinColumn(name = "Hotel_Code", referencedColumnName = "code", nullable = false)
    private Housing housing;

    @ManyToOne
    @JoinColumn(name = "Hotel_Name", referencedColumnName = "name", nullable = false)
    private Housing housing_name;

    



    public Reservation(){}

    public Reservation(Integer id, User client, Housing housing, Date check_in, Date check_out) {
        this.id = id;
        this.client = client;
        this.housing = housing;
        this.check_in = check_in;
        this.check_out = check_out;
        this.valorated = false;
        this.housing_name = housing; 
    }
    public Reservation(Integer id, User client, Housing housing, Date check_in, Date check_out, boolean valorated) {
        this.id = id;
        this.client = client;
        this.housing = housing;
        this.check_in = check_in;
        this.check_out = check_out;
        this.valorated = valorated;
        this.housing_name = housing;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public User getID_cliente() {
        return this.client;
    }

    public void setID_cliente(User Client_ID) {
        this.client = Client_ID;
    }

    public Housing getHousing() {
        return housing;
    }

    public void setHousing(Housing housing) {
        this.housing = housing;
    }
}
