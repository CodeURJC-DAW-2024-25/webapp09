package es.grupo9.practica1.entities;


import jakarta.persistence.*;

@Entity

public class Client extends User {

    public Client(){}
    
    public Client(String dni, String name, Integer number, String password, String email) {
        super(dni, name, number, password, email);
        this.admin = false;
    }

}
