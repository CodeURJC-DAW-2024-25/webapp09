package es.grupo9.practica1.entities;

import java.util.List;



import jakarta.persistence.*;

@Entity
@Table(name = "User")

public class User {
    @Id
    @Column(name = "dni", nullable = false, unique = true, length = 9)
    private String dni;

    @Column(name = "name", nullable = false, unique = true ,length = 100)
    private String name;

    @Column(name = "number", nullable = false, unique = true, length = 9)
    private Integer number;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Column(name = "email", nullable = false, unique = true, length = 40)
    private String email;

    @Column(name = "is_admin", nullable = false)
    protected Boolean admin;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    @Column(name = "encoded_password", nullable = true)
    private String encodedPassword;

    public User() {
    }




    public User(String dni, String name, Integer number, String password, String email) {
        this.dni = dni;
        this.name = name;
        this.number = number;
        this.password = password;
        this.email = email;
        this.admin = false;
    }

    public void setAdmin(boolean admin) {

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

    public Boolean getAdmin() {
        return admin;
    }
    

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    @Override
    public String toString() {
        return "Comment [dni=" + dni + ", nombre=" + name + ", numero=" + number + ", contrasenia=" + password
                + ", correo=" + email + "]";
    }
}
