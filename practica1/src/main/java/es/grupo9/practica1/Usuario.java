package es.grupo9.practica1;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @Column(name = "dni", nullable = false, unique = true, length = 9)
    private String dni;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "numero", nullable = false, unique = true, length = 9)
    private Integer numero;

    @Column(name = "contrasenia", nullable = false, length = 20)
    private String contrasenia;

    @Column(name = "correo", nullable = false, unique = true, length = 40)
    private String correo;

    @Column(name = "admin", nullable = false)
    protected boolean admin;

    public Usuario() {
    }

    public Usuario(String dni, String nombre, Integer numero, String contrasenia, String correo) {
        this.dni = dni;
        this.nombre = nombre;
        this.numero = numero;
        this.contrasenia = contrasenia;
        this.correo = correo;
    }

    public void setAdmin(boolean admin){

        this.admin = admin;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
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
