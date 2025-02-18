package es.grupo9.practica1;


import jakarta.persistence.*;
import java.util.List;

@Entity
public class Alojamiento {
    @Id
    private int codigo;
    private String ubicacion;
    private String nombre;
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
