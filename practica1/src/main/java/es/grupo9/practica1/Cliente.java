package es.grupo9.practica1;


import javax.persistence.*;
@Entity
public class Cliente extends Usuario {

    public Cliente(int dni, String nombre, int numero, String constraseña, String correo) {
        super(dni, nombre, numero, constraseña, correo);
        this.AID = 0;
    }

}
