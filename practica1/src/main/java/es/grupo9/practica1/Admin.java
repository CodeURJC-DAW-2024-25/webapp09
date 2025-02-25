package es.grupo9.practica1;

import jakarta.persistence.*;

@Entity
public class Admin extends User{

    public Admin(){}

    public Admin(String dni, String name, int number, String password, String email) {
        super(dni, name, number, password, email);
        this.admin = true;
    }
    
    

}
