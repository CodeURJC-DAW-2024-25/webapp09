package es.grupo9.practica1;


import jakarta.persistence.*;
import java.util.List;

@Entity
public class Alojamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(name = "ubicacion", nullable = false)
    private String ubicacion;
    @Column(name = "nombre", nullable = false, unique = true, length = 100)
    private String nombre;
    @Column(name = "imagen", length = 100)
    private String imagen;

    public Alojamiento(int codigo, String ubicacion, String nombre, String imagen) {
        this.codigo = codigo;
        this.ubicacion = ubicacion;
        this.nombre = nombre;
        this.imagen = imagen;
    }
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
