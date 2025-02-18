package es.grupo9.practica1;

import java.sql.Blob;
import jakarta.persistence.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dni;

    private String nombre;
    private int numero;
    private String constraseña;
    private String correo;

    protected int AID;

    public Usuario(int dni, String nombre, int numero, String constraseña, String correo) {
        this.dni = dni;
        this.nombre = nombre;
        this.numero = numero;
        this.constraseña = constraseña;
        this.correo = correo;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getConstraseña() {
        return constraseña;
    }

    public void setConstraseña(String constraseña) {
        this.constraseña = constraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
