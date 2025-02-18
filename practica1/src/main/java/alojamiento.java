

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Alojamiento {
    @Id
    private String codigo;
    private String ubicacion;
    private String nombre;
    private String imagen;

    public alojamiento(String codigo, String ubicacion, String nombre, String URL) {
        this.codigo = codigo;
        this.ubicacion = ubicacion;
        this.nombre = nombre;
        this.imagen = imagen;
    }
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
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
