package es.grupo9.practica1;

import java.sql.Blob;
import jakarta.persistence.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dni;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "numero", nullable = false, unique = true, length = 9)
    private int numero;

    @Column(name = "contrasenia", nullable = false, unique = true, length = 20)
    private String constraseña;

    @Column(name = "correo", nullable = false, unique = true, length = 40)
    private String correo;

    protected boolean admin;

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
