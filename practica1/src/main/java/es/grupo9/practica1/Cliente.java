package es.grupo9.practica1;


import jakarta.persistence.*;

@Entity
public class Cliente extends Usuario {

    public Cliente(){}
    
    public Cliente(int dni, String nombre, int numero, String constraseña, String correo) {
        super(dni, nombre, numero, constraseña, correo);
        this.admin = false;
    }

}
