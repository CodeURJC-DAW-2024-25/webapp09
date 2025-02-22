package es.grupo9.practica1;

//import java.sql.Blob;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dni;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "numero", nullable = false, unique = true, length = 9)
    private int numero;

    @Column(name = "contrasenia", nullable = false, length = 20)
    private String contrasenia;

    @Column(name = "correo", nullable = false, unique = true, length = 40)
    private String correo;

    @Column(name = "admin", nullable = false)
    protected boolean admin;

    public Usuario(int dni, String nombre, int numero, String contrasenia, String correo) {
        this.dni = dni;
        this.nombre = nombre;
        this.numero = numero;
        this.contrasenia = contrasenia;
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

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    @Override
	public String toString() {
		return "Comment [dni=" + dni+ ", nombre=" + nombre + ", numero=" + numero + ", contrasenia=" + contrasenia + ", correo=" + correo + "]";
	}
}
