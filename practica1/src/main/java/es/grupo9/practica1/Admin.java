package es.grupo9.practica1;

import jakarta.persistence.*;

@Entity
public class Admin extends Usuario{

    public Admin(int dni, String nombre, int numero, String constraseña, String correo) {
        super(dni, nombre, numero, constraseña, correo);
        this.AID = 1;
    }


}
