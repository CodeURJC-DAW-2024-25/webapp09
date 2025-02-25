package es.grupo9.practica1;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "dni", nullable = false, unique = true, length = 9)
    private String dni;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "number", nullable = false, unique = true, length = 9)
    private Integer number;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Column(name = "email", nullable = false, unique = true, length = 40)
    private String email;

    @Column(name = "is_admin", nullable = false)
    protected boolean admin;

    public User() {
    }

    public User(String dni, String name, Integer number, String password, String email) {
        this.dni = dni;
        this.name = name;
        this.number = number;
        this.password = password;
        this.email = email;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
	public String toString() {
		return "Comment [dni=" + dni+ ", nombre=" + name + ", numero=" + number + ", contrasenia=" + password + ", correo=" + email + "]";
	}
}
